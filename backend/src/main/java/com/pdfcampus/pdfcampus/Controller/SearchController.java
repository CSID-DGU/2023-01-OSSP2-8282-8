package com.pdfcampus.pdfcampus.Controller;


import com.pdfcampus.pdfcampus.dto.DetailBookDto;
import com.pdfcampus.pdfcampus.dto.DetailNoteDto;
import com.pdfcampus.pdfcampus.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class SearchController {
    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search/books")
    public ResponseEntity<Map<String, Object>> getBooksByKeyword(@RequestParam("keyword") String keyword, @RequestParam("userId") String userId) {
        List<DetailBookDto> contentsList = searchService.searchBooks(keyword, userId);

        Map<String, Object> responseBody = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("contentsList", contentsList);
        responseBody.put("data", data);

        Map<String, Object> apiStatus = new HashMap<>();
        apiStatus.put("errorMessage", "");
        apiStatus.put("errorCode", "N200");
        responseBody.put("apiStatus", apiStatus);

        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/search/notes")
    public ResponseEntity<Map<String, Object>> getNotesByKeyword(@RequestParam("keyword") String keyword, @RequestParam("userId") String userId) {
        List<DetailNoteDto> contentsList = searchService.searchNotes(keyword);

        Map<String, Object> responseBody = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("contentsList", contentsList);
        responseBody.put("data", data);

        Map<String, Object> apiStatus = new HashMap<>();
        apiStatus.put("errorMessage", "");
        apiStatus.put("errorCode", "N200");
        responseBody.put("apiStatus", apiStatus);

        return ResponseEntity.ok(responseBody);
    }
}
