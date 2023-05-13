package com.pdfcampus.pdfcampus.Controller;

import com.pdfcampus.pdfcampus.dto.MylibBookDto;
import com.pdfcampus.pdfcampus.dto.MylibNoteDto;
import com.pdfcampus.pdfcampus.service.DetailService;
import com.pdfcampus.pdfcampus.service.MainService;
import com.pdfcampus.pdfcampus.service.MylibService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class MainController {
    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/main")
    public ResponseEntity<Map<String, Object>> getNewBooksAndNotes() {
        List<MylibBookDto> newBooks = mainService.getMainBookData();
        List<MylibNoteDto> newNotes = mainService.getMainNoteData();

        Map<String, Object> responseBody = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("newBooks", newBooks);
        data.put("newNotes", newNotes);
        responseBody.put("data", data);

        Map<String, Object> apiStatus = new HashMap<>();
        apiStatus.put("errorMessage", "");
        apiStatus.put("errorCode", "N200");
        responseBody.put("apiStatus", apiStatus);

        return ResponseEntity.ok(responseBody);
    }
}
