package com.pdfcampus.pdfcampus.service;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;

@Service
public class AmazonS3ClientService {
    private AmazonS3 s3client;

    @Autowired
    public AmazonS3ClientService(AmazonS3 s3client) {
        this.s3client = s3client;
    }

    public byte[] downloadFile(String bucketName, String keyName) {
        S3Object s3object = s3client.getObject(bucketName, keyName);
        S3ObjectInputStream inputStream = s3object.getObjectContent();
        byte[] content;
        try {
            content = IOUtils.toByteArray(inputStream);
            processPDF(content);
        } catch (IOException e) {
            throw new RuntimeException("Failed to download content from S3", e);
        }
        return content;
    }

    public void processPDF(byte[] pdfContent) throws IOException {
        PDDocument document = PDDocument.load(new ByteArrayInputStream(pdfContent));

        PDFTextStripper pdfStripper = new PDFTextStripper() {
            @Override
            protected void writeString(String text, List<TextPosition> textPositions) throws IOException {
                for (TextPosition textPosition : textPositions) {
                    float endX = textPosition.getXDirAdj() + (textPosition.getWidthDirAdj());

                    // DB에 저장
                    System.out.println("String: [" + text + "], Position: [" + textPosition.getXDirAdj() + ", " + endX + ", " + textPosition.getYDirAdj() + "]");
                }
            }
        };

        for (int page = 0; page < document.getNumberOfPages(); ++page) {
            pdfStripper.setStartPage(page);
            pdfStripper.setEndPage(page);
            String text = pdfStripper.getText(document);

            // DB에 저장
            System.out.println("Page: [" + (page + 1) + "], Text: [" + text + "]");
        }

        document.close();
    }
}
