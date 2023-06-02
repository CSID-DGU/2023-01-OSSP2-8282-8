package com.pdfcampus.pdfcampus.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.pdfcampus.pdfcampus.entity.Book;
import com.pdfcampus.pdfcampus.entity.Page;
import com.pdfcampus.pdfcampus.entity.RowNum;
import com.pdfcampus.pdfcampus.repository.DetailBookRepository;
import com.pdfcampus.pdfcampus.repository.PageRepository;
import com.pdfcampus.pdfcampus.repository.RowNumRepository;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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

        // Initialize the PDF splitter
        Splitter splitter = new Splitter();

        // Split the pages
        List<PDDocument> Pages = splitter.split(document);

        for (int pageNumber = 0; pageNumber < Pages.size(); ++pageNumber) {
            Book book = detailBookRepository.findByBid(bid).orElse(null);

            if (book == null) {
                System.out.println("Book with ID: " + bid + " not found.");
                continue;
            }

            Page pageEntity = pageRepository.findByBidAndPageNumber(book.getBid(), pageNumber + 1)
                    .orElse(new Page());

            pageEntity.setBid(book.getBid());
            pageEntity.setPageNumber(pageNumber+1);

            // Save each page to a ByteArrayOutputStream
            ByteArrayOutputStream pdfPage = new ByteArrayOutputStream();
            Pages.get(pageNumber).save(pdfPage);

            InputStream is = new ByteArrayInputStream(pdfPage.toByteArray());

            String s3Key = book.getBookTitle() + "/page" + pageNumber + ".pdf";
            String bucketName = "8282book";

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("application/pdf");
            metadata.setContentDisposition("inline");
            s3client.putObject(new PutObjectRequest(bucketName, s3Key, is, metadata));


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
            // Check if a RowNum already exists for this page and row number
            RowNum rowNumEntity = rowNumRepository.findByPidAndRowNumber(associatedPage.getPid(), rowNumber)
                    .orElse(new RowNum());

            rowNumEntity.setPid(associatedPage.getPid());
            rowNumEntity.setRowNumber(rowNumber++);

            rowNumRepository.save(rowNumEntity);
            System.out.println("Row: [" + text + "], Position: [" + startX + ", " + endX + ", " + y + "]");
        }
    }
}
