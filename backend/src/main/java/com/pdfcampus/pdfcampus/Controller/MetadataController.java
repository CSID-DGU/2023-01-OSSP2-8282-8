package com.pdfcampus.pdfcampus.Controller;
import com.pdfcampus.pdfcampus.dto.ExtractedTextInfo;
import com.pdfcampus.pdfcampus.service.PdfMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.*;

@RestController
@RequestMapping("/note/metadata")
public class MetadataController {

    @Autowired
    private PdfMetadataService pdfMetadataService;

    @PostMapping("/{bookId}")
    public ResponseEntity<?> processTextAndLocationData(@PathVariable Integer bookId, @RequestBody Map<String, Object> body) {
        try {
            Map<String, Object> response = new HashMap<>();
            List<Map<String, Object>> metadata = new ArrayList<>();

            Map<String, Object> data = (Map<String, Object>) body.get("data");
            List<List<Object>> dataList = (List<List<Object>>) data.get("metadata");

            for (List<Object> item : dataList) {
                Integer pageNumber = (Integer) item.get(0);
                List<Object> positionInfo = (List<Object>) item.get(1);

                float startX = ((Number) positionInfo.get(0)).floatValue();
                float endX = ((Number) positionInfo.get(1)).floatValue();
                float y = ((Number) positionInfo.get(2)).floatValue();

                float width = endX - startX;
                float height = 1;

                ExtractedTextInfo extractedTextInfo = pdfMetadataService.extractTextFromLocation(bookId, pageNumber, startX, y, width, height);
                String extractedText = extractedTextInfo.getText();

                Map<String, Object> itemMetadata = new HashMap<>();
                itemMetadata.put("positionInfo", positionInfo);
                itemMetadata.put("extractedText", extractedText);

                metadata.add(itemMetadata);
            }

            // Retrieve all pages for the given bookId
            List<Integer> allPages = pdfMetadataService.getAllPages(bookId);

            response.put("metadata", metadata);
            response.put("pages", allPages);
            response.put("apiStatus", Map.of("errorMessage", "", "errorCode", "N200"));

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "apiStatus", Map.of(
                            "errorMessage", e.getMessage(),
                            "errorCode", "E500"
                    )
            ));
        }
    }
}

