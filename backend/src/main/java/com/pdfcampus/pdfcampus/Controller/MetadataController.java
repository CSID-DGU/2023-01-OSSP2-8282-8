package com.pdfcampus.pdfcampus.Controller;
import com.pdfcampus.pdfcampus.dto.ExtractedTextInfo;
import com.pdfcampus.pdfcampus.service.PdfMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
            Map<String, List<Map<String, Object>>> metadata = new HashMap<>();

            Map<String, Object> data = (Map<String, Object>) body.get("data");
            Map<String, List<List<Object>>> dataList = (Map<String, List<List<Object>>>) data.get("metadata");

            for (Map.Entry<String, List<List<Object>>> entry : dataList.entrySet()) {
                String pageNumber = entry.getKey();
                List<List<Object>> items = entry.getValue();
                List<Map<String, Object>> pageInfo = new ArrayList<>();

                for (List<Object> item : items) {
                    float startX = ((Number) item.get(0)).floatValue();
                    float endX = ((Number) item.get(1)).floatValue();
                    float y = ((Number) item.get(2)).floatValue();

                    float width = endX - startX;
                    float height = 5;

                    ExtractedTextInfo extractedTextInfo = pdfMetadataService.extractTextFromLocation(bookId, Integer.parseInt(pageNumber), startX, y, width, height);
                    String extractedText = extractedTextInfo.getText();

                    // Trim the text to at most 5 words
                    String[] words = extractedText.split(" ");
                    if (words.length > 5) {
                        extractedText = String.join(" ", Arrays.copyOfRange(words, 0, 5));
                    }

                    Map<String, Object> itemMetadata = new HashMap<>();
                    itemMetadata.put("positionInfo", item);
                    itemMetadata.put("extractedText", extractedText);

                    pageInfo.add(itemMetadata);
                }


                metadata.put(pageNumber, pageInfo);
            }


            List<Integer> allPages = pdfMetadataService.getAllPages(bookId);

            response.put("metadata", metadata);
            response.put("pages", allPages);
            response.put("apiStatus", Map.of("errorMessage", "", "errorCode", "N200"));

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "apiStatus", Map.of(
                            "errorMessage", e.getMessage() == null ? "Unknown server error" : e.getMessage(),
                            "errorCode", "E500"
                    )
            ));
        }
    }
}