package com.pdfcampus.pdfcampus.service;

import com.pdfcampus.pdfcampus.entity.Book;
import com.pdfcampus.pdfcampus.entity.Page;
import com.pdfcampus.pdfcampus.entity.Text;
import com.pdfcampus.pdfcampus.entity.User;
import com.pdfcampus.pdfcampus.repository.BookRepository;
import com.pdfcampus.pdfcampus.repository.DetailBookRepository;
import com.pdfcampus.pdfcampus.repository.PageRepository;
import com.pdfcampus.pdfcampus.repository.TextRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PdfMetadataService {
    @Autowired
    private PageRepository pageRepository;
    @Autowired
    private TextRepository textRepository;
    @Autowired
    private DetailBookRepository detailBookRepository;

    public void processPDF(byte[] pdfContent, Integer bid) throws IOException {
        PDDocument document = PDDocument.load(new ByteArrayInputStream(pdfContent));

        for (int pageNumber = 0; pageNumber < document.getNumberOfPages(); ++pageNumber) {
            Book book = detailBookRepository.findByBid(bid).orElse(null);

            if (book == null) {
                System.out.println("Book with ID: " + bid + " not found.");
                continue;
            }

            Page pageEntity = new Page();
            pageEntity.setBid(book.getBid());
            pageEntity.setPageNumber(pageNumber + 1);
            pageEntity = pageRepository.save(pageEntity);

            PDFTextStripper pdfStripper = new CustomPDFTextStripper(pageEntity);
            pdfStripper.setStartPage(pageNumber);
            pdfStripper.setEndPage(pageNumber);

            String text = pdfStripper.getText(document);

            System.out.println("Page: [" + (pageNumber + 1) + "], Text: [" + text + "]");
        }

        document.close();
    }

    private class CustomPDFTextStripper extends PDFTextStripper {
        private Page associatedPage;

        public CustomPDFTextStripper(Page associatedPage) throws IOException {
            super();
            this.associatedPage = associatedPage;
        }

        @Override
        protected void writeString(String text, List<TextPosition> textPositions) throws IOException {
            String fullText = "";
            float startY = -1, startX = -1, endX = -1;
            for (TextPosition textPosition : textPositions) {
                if (startY == -1 || textPosition.getYDirAdj() == startY) {
                    if (startY == -1) {
                        startY = textPosition.getYDirAdj();
                        startX = textPosition.getXDirAdj();
                    }
                    fullText += textPosition.getUnicode();
                    endX = textPosition.getXDirAdj() + textPosition.getWidthDirAdj();
                } else {
                    String position = "[" + startX + ", " + endX + ", " + startY + "]";
                    Text textEntity = new Text();
                    textEntity.setPosition(position);
                    textEntity.setPage(associatedPage);
                    textRepository.save(textEntity);

                    System.out.println("String: [" + fullText + "], Position: [" + startX + ", " + endX + ", " + startY + "]");

                    startY = textPosition.getYDirAdj();
                    startX = textPosition.getXDirAdj();
                    endX = textPosition.getXDirAdj() + textPosition.getWidthDirAdj();
                    fullText = textPosition.getUnicode();
                }
            }
            if (!fullText.isEmpty()) {
                String position = "[" + startX + ", " + endX + ", " + startY + "]";
                Text textEntity = new Text();
                textEntity.setPosition(position);
                textEntity.setPage(associatedPage);
                textRepository.save(textEntity);

                System.out.println("String: [" + fullText + "], Position: [" + startX + ", " + endX + ", " + startY + "]");
            }
        }

    }
}


