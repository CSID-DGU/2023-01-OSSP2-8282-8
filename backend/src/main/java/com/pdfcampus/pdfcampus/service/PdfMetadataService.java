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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            setSortByPosition(true);
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

                    // pdfBoxPageHeight == 841
                    if (y >= 70 && y <= (841 - 110)) {
                        fullTextBuilder.append(textPosition.getUnicode());
                        endX = textPosition.getEndX();
                        lastY = y;
                    }
                } else {
                    saveRow(fullTextBuilder.toString(), startX, endX, lastY);
                    startX = textPosition.getX();
                    y = textPosition.getY();
                    endX = textPosition.getEndX();
                    fullTextBuilder.setLength(0);

                    if (y >= 70 && y <= (841 - 110)) {
                        fullTextBuilder.append(textPosition.getUnicode());
                        lastY = y;
                    }
                }
            }

            if (fullTextBuilder.length() > 1 && lastY <= (841-110)) {
                saveRow(fullTextBuilder.toString(), startX, endX, lastY);
            }
        }


        private void saveRow(String text, float startX, float endX, float y) {
            // TextPosition은 문자열이 없는 경우에도, 빈 공간을 만들어낸다. 이것을 한 번 더 걸러준다.
            if (text.trim().isEmpty()) {
                return;
            }

            // 이전에 저장된 rowY 값을 가져옴
            Optional<RowNum> lastRowEntityOpt = rowNumRepository.findTopByPidOrderByRowYDesc(associatedPage.getPid());
            float lastRowY = lastRowEntityOpt.isPresent() ? lastRowEntityOpt.get().getRowY() : -1.0f;

            float tolerance = 5.0f; // 이 범위 내에는 같은 행 (미세)
            if (Math.abs(y - lastRowY) < tolerance) {
                System.out.println("Skipping row due to duplicate Y values.");
                return;
            }

            // 이미 존재하는지 확인
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
        float cutOff = pdfBoxPageHeight - requestPageHeight;
        System.out.println("cutOff ="+ cutOff);
        // Check if the page exists in the database
        Page page = pageRepository.findByBidAndPageNumber(bid, pageNumber)
                .orElseThrow(() -> new RuntimeException("Page number: " + pageNumber + " for book with ID: " + bid + " not found."));

        // Get the PDF from S3
        S3Object s3Object = s3client.getObject("8282book", bid + ".pdf");
        InputStream objectData = s3Object.getObjectContent();
        byte[] bytes = IOUtils.toByteArray(objectData);

        // Load the PDF document
        PDDocument document = PDDocument.load(new ByteArrayInputStream(bytes));
        System.out.println("pdf 가져옴");
        // Create a new instance of PDFTextStripperByArea
        PDFTextStripperByArea stripper = new PDFTextStripperByArea();

        Rectangle2D.Float rect = new Rectangle2D.Float(startX, startY+cutOff, width, height);


        // Add the defined area to the stripper
        stripper.addRegion("region", rect);


        // Extract the text from the defined area
        stripper.extractRegions(document.getPage(pageNumber - 1));
        String text = stripper.getTextForRegion("region");

        // Here we would typically determine the row number and y position
        // but without an actual PDF and logic to calculate the row number, this is not possible
        // So for now, let's set them to some arbitrary values
        int rowNumber = 1;
        float y = startY+cutOff;
        System.out.println(startY);
        // If the extracted text is empty, whitespace, or newline characters, find the nearest y row and extract the text from that row
        float deltaY = 1.0f;  // The amount by which to change y
        while (text.isBlank() || text.equals("\r\n")) {
            y += deltaY;  // Change y
            Rectangle2D.Float nearestRect = new Rectangle2D.Float(startX, y, width, height);
            stripper.addRegion("nearestRegion", nearestRect);
            stripper.extractRegions(document.getPage(pageNumber - 1));
            text = stripper.getTextForRegion("nearestRegion");
        }
        RowNum upperRow = rowNumRepository.findFirstByPidAndRowYGreaterThanEqualOrderByRowYAsc(page.getPid(), y);
        System.out.println(upperRow.getRowY());
        RowNum lowerRow = rowNumRepository.findFirstByPidAndRowYLessThanOrderByRowYDesc(page.getPid(), y);
        System.out.println(lowerRow.getRowY());

        RowNum rowNum;
        if (upperRow == null) {
            rowNum = lowerRow;
        } else if (lowerRow == null) {
            rowNum = upperRow;
        } else {
            rowNum = Math.abs(upperRow.getRowY() - y) < Math.abs(lowerRow.getRowY() - y) ? upperRow : lowerRow;
        }

        ExtractedTextInfo extractedTextInfo = new ExtractedTextInfo();
        extractedTextInfo.setText(text);
        extractedTextInfo.setRowNumber(rowNum.getRowNumber());  // 사용하지 않음
        extractedTextInfo.setYPosition(y);

        document.close();
        objectData.close();

        return extractedTextInfo;
    }

    public List<Integer> getAllPages(Integer bookId) {
        List<Page> pages = pageRepository.findByBid(bookId);
        List<Integer> pageNumbers = new ArrayList<>();

        for (Page page : pages) {
            pageNumbers.add(page.getPageNumber());
        }

        return pageNumbers;
    }

}