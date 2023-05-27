package com.pdfcampus.pdfcampus.service;

import com.pdfcampus.pdfcampus.entity.Page;
import com.pdfcampus.pdfcampus.entity.Text;
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
    public void processPDF(byte[] pdfContent, Integer bid) throws IOException {
        PDDocument document = PDDocument.load(new ByteArrayInputStream(pdfContent));

        PDFTextStripper pdfStripper = new PDFTextStripper() {
            @Override
            protected void writeString(String text, List<TextPosition> textPositions) throws IOException {
                for (TextPosition textPosition : textPositions) {
                    float endX = textPosition.getXDirAdj() + (textPosition.getWidthDirAdj());

                    // DB에 저장
                    String position = "[" + textPosition.getXDirAdj() + ", " + endX + ", " + textPosition.getYDirAdj() + "]";
                    Text textEntity = new Text();
                    textEntity.setPosition(position);
                    textRepository.save(textEntity);
                    System.out.println("String: [" + text + "], Position: [" + textPosition.getXDirAdj() + ", " + endX + ", " + textPosition.getYDirAdj() + "]");
                }
            }
        };

        for (int page = 0; page < document.getNumberOfPages(); ++page) {
            pdfStripper.setStartPage(page);
            pdfStripper.setEndPage(page);

            // DB에 저장
            String text = pdfStripper.getText(document);
            Page pageEntity = new Page();
            pageEntity.setBid(bid);
            pageEntity.setPageNumber(page + 1);
            pageRepository.save(pageEntity);
            System.out.println("Page: [" + (page + 1) + "], Text: [" + text + "]");
        }

        document.close();
    }
}
