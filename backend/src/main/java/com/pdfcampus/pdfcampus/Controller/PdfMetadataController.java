package com.pdfcampus.pdfcampus.Controller;

import com.pdfcampus.pdfcampus.service.PdfMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class PdfMetadataController {
    @Autowired
    private PdfMetadataService pdfMetadataService;

    @PostMapping("/pdf/metadata")
    public ResponseEntity<String> processPdfMetadata(@RequestPart("file") MultipartFile file, Integer bid) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("PDF file is required.");
            }

            byte[] pdfContent = file.getBytes();
            pdfMetadataService.processPDF(pdfContent, bid);

            return ResponseEntity.ok("PDF metadata processed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process PDF metadata.");
        }
    }
}
