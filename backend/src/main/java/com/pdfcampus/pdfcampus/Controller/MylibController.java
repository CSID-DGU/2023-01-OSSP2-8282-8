package com.pdfcampus.pdfcampus.Controller;

import com.pdfcampus.pdfcampus.entity.Book;
import com.pdfcampus.pdfcampus.entity.Mylib;
import com.pdfcampus.pdfcampus.entity.Note;
import com.pdfcampus.pdfcampus.service.MylibService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class MylibController {
    private final MylibService mylibService;

    public MylibController(MylibService mylibService) {
        this.mylibService = mylibService;
    }

    @GetMapping("/mylib")
    public ResponseEntity<Map<String, Object>> getMylibData() {
        List<Mylib> mylibList = mylibService.getMylibList();

        // Note와 Book을 분리해서 리스트로 저장
        List<Note> noteList = mylibList.stream()
                .map(Mylib::getNote)
                .filter(note -> note != null)
                .collect(Collectors.toList());

        List<Book> bookList = mylibList.stream()
                .map(Mylib::getBook)
                .filter(book -> book != null)
                .collect(Collectors.toList());

        // Response Body 구성
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("noteList", noteList);
        responseBody.put("bookList", bookList);

        // API Status 구성
        Map<String, String> apiStatus = new HashMap<>();
        apiStatus.put("errorMessage", "");
        apiStatus.put("errorCode", "N200");

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
