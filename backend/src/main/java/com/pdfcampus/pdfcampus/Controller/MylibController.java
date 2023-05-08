package com.pdfcampus.pdfcampus.Controller;

import com.pdfcampus.pdfcampus.dto.MylibBookDto;
import com.pdfcampus.pdfcampus.dto.MylibDto;
import com.pdfcampus.pdfcampus.dto.MylibNoteDto;
import com.pdfcampus.pdfcampus.service.MylibService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class MylibController {
    private final MylibService mylibService;

    public MylibController(MylibService mylibService) {
        this.mylibService = mylibService;
    }

    @GetMapping("/mylib/{userId}")
    public ResponseEntity<Map<String, Object>> getMylibData(@PathVariable String userId) {
        try {
            MylibDto mylibData = mylibService.getMylibData(userId);

            // Note와 Book을 분리해서 리스트로 저장
            List<MylibNoteDto> noteList = mylibData.getNoteList();
            List<MylibBookDto> bookList = mylibData.getBookList();

            // Response Body 구성
            Map<String, Object> responseBody = new LinkedHashMap<>();
            Map<String, Object> dataMap = new LinkedHashMap<>();
            dataMap.put("noteList", noteList);
            dataMap.put("bookList", bookList);
            responseBody.put("data", dataMap);

            // 200
            Map<String, String> apiStatus = new LinkedHashMap<>();
            apiStatus.put("errorMessage", "");
            apiStatus.put("errorCode", "N200");
            responseBody.put("apiStatus", apiStatus);

            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (Exception e) {
            // 기타 예외가 발생한 경우
            Map<String, Object> responseBody = new LinkedHashMap<>();
            responseBody.putIfAbsent("data", Map.of("noteList", List.of(), "bookList", List.of()));

            // 500
            Map<String, String> apiStatus = new HashMap<>();
            apiStatus.put("errorMessage", "서버 오류가 발생했습니다.");
            apiStatus.put("errorCode", "N500");
            responseBody.put("apiStatus", apiStatus);

            return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/mylib/bookAll/{userId}")
    public ResponseEntity<Map<String, Object>> getMylibBookData(@PathVariable String userId) {
        try {
            List<MylibBookDto> bookList = mylibService.getMylibBookData(userId);

            // Response Body 구성
            Map<String, Object> responseBody = new LinkedHashMap<>();
            responseBody.put("data", Map.of("bookList", bookList));

            // API Status 구성
            Map<String, String> apiStatus = new HashMap<>();
            apiStatus.put("errorMessage", "");
            apiStatus.put("errorCode", "N200");
            responseBody.put("apiStatus", apiStatus);

            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (Exception e) {
            // 기타 예외가 발생한 경우
            Map<String, Object> responseBody = new LinkedHashMap<>();
            responseBody.put("data", Map.of("bookList", List.of()));

            // API Status 구성
            Map<String, String> apiStatus = new HashMap<>();
            apiStatus.put("errorMessage", "서버 오류가 발생했습니다.");
            apiStatus.put("errorCode", "N500");
            responseBody.put("apiStatus", apiStatus);

            return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/mylib/noteAll/{userId}")
    public ResponseEntity<Map<String, Object>> getMylibNoteData(@PathVariable String userId) {
        try {
            List<MylibNoteDto> noteList = mylibService.getMylibNoteData(userId);

            // Response Body 구성
            Map<String, Object> responseBody = new LinkedHashMap<>();
            responseBody.put("data", Map.of("noteList", noteList));

            // API Status 구성
            Map<String, String> apiStatus = new HashMap<>();
            apiStatus.put("errorMessage", "");
            apiStatus.put("errorCode", "N200");
            responseBody.put("apiStatus", apiStatus);

            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (Exception e) {
            // 기타 예외가 발생한 경우
            Map<String, Object> responseBody = new LinkedHashMap<>();
            responseBody.put("data", Map.of("noteList", List.of()));

            // API Status 구성
            Map<String, String> apiStatus = new HashMap<>();
            apiStatus.put("errorMessage", "서버 오류가 발생했습니다.");
            apiStatus.put("errorCode", "N500");
            responseBody.put("apiStatus", apiStatus);

            return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
