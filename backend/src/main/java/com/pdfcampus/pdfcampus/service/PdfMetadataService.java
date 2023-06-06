package com.pdfcampus.pdfcampus.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.pdfcampus.pdfcampus.dto.ExtractedTextInfo;
import com.pdfcampus.pdfcampus.entity.Book;
import com.pdfcampus.pdfcampus.entity.Page;
import com.pdfcampus.pdfcampus.entity.RowNum;
import com.pdfcampus.pdfcampus.repository.DetailBookRepository;
import com.pdfcampus.pdfcampus.repository.PageRepository;
import com.pdfcampus.pdfcampus.repository.RowNumRepository;
import com.pdfcampus.pdfcampus.service.GetPDFDimensions;
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.apache.pdfbox.text.TextPosition;
import org.modelmapper.internal.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.List;

@Service
public class PdfMetadataService {
    @Autowired
    private PageRepository pageRepository;
    @Autowired
    private RowNumRepository rowNumRepository;
    @Autowired
    private DetailBookRepository detailBookRepository;
    @Autowired
    private AmazonS3 s3client;


    public void processPDF(byte[] pdfContent, Integer bid) throws IOException {
        PDDocument document = PDDocument.load(new ByteArrayInputStream(pdfContent));
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        int rowNumber = 1;

        for (int pageNumber = 0; pageNumber < document.getNumberOfPages(); ++pageNumber) {
            Book book = detailBookRepository.findByBid(bid).orElse(null);

            if (book == null) {
                System.out.println("Book with ID: " + bid + " not found.");
                continue;
            }

            // Check if a Page already exists for this book and page number
            Page pageEntity = pageRepository.findByBidAndPageNumber(book.getBid(), pageNumber + 1)
                    .orElse(new Page());

            pageEntity.setBid(book.getBid());
            pageEntity.setPageNumber(pageNumber+1);

            float[] dimensions = GetPDFDimensions.getPageDimensions(document, pageNumber);
            System.out.println("Page Width: " + dimensions[0] + ", Page Height: " + dimensions[1]);

            // Render the page to an image and save it to S3
            BufferedImage bim = pdfRenderer.renderImageWithDPI(pageNumber, 300);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bim, "png", os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());

            String s3Key = book.getBookTitle() + "/page" + pageNumber + ".png";
            String bucketName = "8282book";
            s3client.putObject(new PutObjectRequest(bucketName, s3Key, is, new ObjectMetadata()));

            // Get the URL of the image we just uploaded
            URL s3Url = s3client.getUrl(bucketName, s3Key);
            pageEntity.setPageUrl(s3Url.toString());

            pageEntity = pageRepository.save(pageEntity);

            System.out.println("Page: [" + (pageNumber + 1) + "]");

            PDFTextStripper pdfStripper = new CustomPDFTextStripper(pageEntity, rowNumber);
            pdfStripper.setStartPage(pageNumber+1);
            pdfStripper.setEndPage(pageNumber+1);

            pdfStripper.getText(document);
        }

        document.close();
    }

    private class CustomPDFTextStripper extends PDFTextStripper {
        private static final float Y_TOLERANCE = 2.0f;

        private Page associatedPage;
        private int rowNumber;
        private float lastY = -1;

        public CustomPDFTextStripper(Page associatedPage, int rowNumber) throws IOException {
            super();
            this.associatedPage = associatedPage;
            this.rowNumber = rowNumber;
        }

        @Override
        protected void writeString(String text, List<TextPosition> textPositions) throws IOException {
            StringBuilder fullTextBuilder = new StringBuilder();
            float startX = -1, endX = -1, y = -1;
            for (TextPosition textPosition : textPositions) {
                if (startX == -1 || Math.abs(textPosition.getY() - lastY) <= Y_TOLERANCE) {
                    if (startX == -1) {
                        startX = textPosition.getX();
                        y = textPosition.getY();
                    }
                    fullTextBuilder.append(textPosition.getUnicode());
                    endX = textPosition.getEndX();
                    lastY = y;
                } else {

                    saveRow(fullTextBuilder.toString(), startX, endX, lastY);

                    startX = textPosition.getX();
                    y = textPosition.getY();
                    endX = textPosition.getEndX();
                    fullTextBuilder.setLength(0);
                    fullTextBuilder.append(textPosition.getUnicode());
                    lastY = y;
                }
            }

            if (fullTextBuilder.length() > 0) {
                saveRow(fullTextBuilder.toString(), startX, endX, lastY);
            }
        }

        private void saveRow(String text, float startX, float endX, float y) {
            // PDFBox 인식 페이지 크기
            float pdfBoxPageWidth = 595;
            float pdfBoxPageHeight = 841;

            // 요청받는 PDF 페이지 크기
            float requestPageWidth = 590;
            float requestPageHeight = 680;

            // 잘린 부분의 크기
            // float cutOff = pdfBoxPageHeight - requestPageHeight;

            // Check if a RowNum already exists for this page and row number
            RowNum rowNumEntity = rowNumRepository.findByPidAndRowNumber(associatedPage.getPid(), rowNumber)
                    .orElse(new RowNum());

            rowNumEntity.setPid(associatedPage.getPid());
            rowNumEntity.setRowNumber(rowNumber++);
            rowNumEntity.setRowY(y);

            rowNumRepository.save(rowNumEntity);
            System.out.println("Row: [" + text + "], Position: [" + startX + ", " + endX + ", " + y + "]");
        }
    }
    public ExtractedTextInfo extractTextFromLocation(Integer bid, Integer pageNumber, float startX, float startY, float width, float height) throws IOException {

        // PDFBox 인식 페이지 크기
        float pdfBoxPageWidth = 595;
        float pdfBoxPageHeight = 841;

        // 요청받는 PDF 페이지 크기
        float requestPageWidth = 590;
        float requestPageHeight = 680;

        // 잘린 부분의 크기
        float cutOff = pdfBoxPageHeight - requestPageHeight -85;

        // pageNumber 체크
        Page page = pageRepository.findByBidAndPageNumber(bid, pageNumber)
                .orElseThrow(() -> new RuntimeException("Page number: " + pageNumber + " for book with ID: " + bid + " not found."));

        // 버킷에서 PDF 가져오기
        S3Object s3Object = s3client.getObject("8282book", bid + ".pdf");
        InputStream objectData = s3Object.getObjectContent();
        byte[] bytes = IOUtils.toByteArray(objectData);

        // PDF 불러오기
        PDDocument document = PDDocument.load(new ByteArrayInputStream(bytes));

        // PDF 면적 일치하도록 설정
        PDFTextStripperByArea stripper = new PDFTextStripperByArea();

        Rectangle2D.Float rect = new Rectangle2D.Float(startX, startY+cutOff, width, height);
        stripper.addRegion("region", rect);

        // text 추출
        stripper.extractRegions(document.getPage(pageNumber - 1));
        String text = stripper.getTextForRegion("region");


        // 받아오는 PDF는 상단이 일부 잘린 PDF이다. 따라서 cutOff를 더해줘서, 동일한 y값을 가지도록 한다.
        float y = startY+cutOff;

        // x,y 좌표가 정확하게 일치하지 않는 경우, text가 추출되지 않는다.
        // 따라서 y값에 조금씩 변화를 주면서 유의미한 text가 나올 때까지 반복한다.
        float deltaY = 1.0f;
        while (text.isBlank() || text.equals("\r\n")) {
            y += deltaY;  // Change y
            Rectangle2D.Float nearestRect = new Rectangle2D.Float(startX, y, width, height);
            stripper.addRegion("nearestRegion", nearestRect);
            stripper.extractRegions(document.getPage(pageNumber - 1));
            text = stripper.getTextForRegion("nearestRegion");
        }

        // 텍스트의 rowNumber를 가져온다.
        // 행이 정확하게 일치하는 경우는 거의 없어서, upperRow와 lowerRow를 설정한다.
        RowNum upperRow = rowNumRepository.findFirstByPidAndRowYGreaterThanEqualOrderByRowYAsc(page.getPid(), y);
        System.out.println(upperRow.getRowY());
        RowNum lowerRow = rowNumRepository.findFirstByPidAndRowYLessThanOrderByRowYDesc(page.getPid(), y);
        System.out.println(lowerRow.getRowY());

        // rowNum에 값을 할당
        RowNum rowNum;
        if (upperRow == null) {
            rowNum = lowerRow;
            System.out.println("upperRow == null"+rowNum);
        } else if (lowerRow == null) {
            rowNum = upperRow;
            System.out.println("lowerRow == null"+rowNum);
        } else { // upperRow와 lowerRow 중 y와의 거리가 더 가까운 행을 선택
            rowNum = Math.abs(upperRow.getRowY() - y) < Math.abs(lowerRow.getRowY() - y) ? upperRow : lowerRow;
            System.out.println("else"+rowNum);
        }

        ExtractedTextInfo extractedTextInfo = new ExtractedTextInfo();
        extractedTextInfo.setText(text);
        extractedTextInfo.setRowNumber(rowNum.getRowNumber());
        extractedTextInfo.setYPosition(y-cutOff);

        document.close();
        objectData.close();

        return extractedTextInfo;
    }

    public Map<Integer, List<Map<String, Object>>> getAllRowNumbers(Integer bookId) {
        List<Page> pages = pageRepository.findByBid(bookId);
        Map<Integer, List<Map<String, Object>>> pageRowNumbers = new HashMap<>();

        for (Page page : pages) {
            List<Map<String, Object>> rowNumbers = new ArrayList<>();
            List<RowNum> rowNums = rowNumRepository.findByPid(page.getPid());
            for (RowNum rowNum : rowNums){
                Map<String, Object> rowMap = new HashMap<>();
                rowMap.put("rowNum", rowNum.getRowNumber());
                rowMap.put("rowY", rowNum.getRowY()-76);
                rowNumbers.add(rowMap);
            }
            pageRowNumbers.put(page.getPageNumber(), rowNumbers);
        }

        return pageRowNumbers;
    }
}