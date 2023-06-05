package com.pdfcampus.pdfcampus.Controller;

import com.pdfcampus.pdfcampus.service.AmazonS3ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PdfMetadataController {
    @Autowired
    private AmazonS3ClientService amazonS3ClientService;

    @PostMapping("/pdf/metadata")
    public ResponseEntity<String> processPdfMetadata(String bucketName, String keyName) {
        try {
            amazonS3ClientService.downloadAndProcessPdf(bucketName, keyName);
            return ResponseEntity.ok("PDF metadata processed successfully.");
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process PDF metadata.");
        }
    }
}
