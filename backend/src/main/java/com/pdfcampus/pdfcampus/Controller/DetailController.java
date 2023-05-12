package com.pdfcampus.pdfcampus.Controller;

import com.pdfcampus.pdfcampus.dto.DetailBookDto;
import com.pdfcampus.pdfcampus.dto.DetailNoteDto;
import com.pdfcampus.pdfcampus.dto.MylibDto;
import com.pdfcampus.pdfcampus.dto.MylibNoteDto;
import com.pdfcampus.pdfcampus.service.DetailService;
import com.pdfcampus.pdfcampus.service.MylibService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping
public class DetailController {
    private final DetailService detailService;

    public DetailController(DetailService detailService) {
        this.detailService = detailService;
    }

    @GetMapping("/book/detail/{bookId}") // 도서 상세정보를 get
    public ResponseEntity<Map<String, Object>> getDetailBookData(@RequestParam("userId") String userId, @RequestParam("bookId") String bookId) {
        try {
            DetailBookDto detailBookData = detailService.getBookData(bookId);

            Map<String, Object> response = new HashMap<>();
            Map<String, Object> responseData = new LinkedHashMap<>();

            responseData.put("bookTitle", detailBookData.getBookTitle()); // response body 구성
            responseData.put("author", detailBookData.getAuthor());
            responseData.put("publisher", detailBookData.getPublisher());
            responseData.put("publicationYear", detailBookData.getPublicationYear());
            responseData.put("bookCover", detailBookData.getBookCover());
            responseData.put("isStored", detailService.isStored(userId, bookId));

            response.put("data", responseData);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // 기타 예외가 발생한 경우
            Map<String, Object> responseBody = new LinkedHashMap<>();

            // 500
            Map<String, String> apiStatus = new HashMap<>();
            apiStatus.put("errorMessage", "서버 오류가 발생했습니다.");
            apiStatus.put("errorCode", "N500");
            responseBody.put("apiStatus", apiStatus);

            return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/note/detail/{noteId}") // 필기 상세 정보를 get
    public ResponseEntity<Map<String, Object>> getDetailNoteData(@RequestParam("userId") String userId, @RequestParam("noteId") String noteId) {
        try {
            DetailNoteDto detailNoteData = detailService.getNoteData(noteId);

            Map<String, Object> response = new HashMap<>();
            Map<String, Object> responseData = new LinkedHashMap<>();
            Map<String, Object> bookInfo = new LinkedHashMap<>();

            bookInfo.put("author", detailNoteData.getBookAuthor()); // bookInfo body 구성
            bookInfo.put("publisher", detailNoteData.getPublisher());
            bookInfo.put("publicationYear", detailNoteData.getPublicationYear());
            bookInfo.put("bookCover", detailNoteData.getBookCover());

            responseData.put("noteTitle", detailNoteData.getNoteTitle()); // response body 구성
            responseData.put("author", detailNoteData.getNoteAuthor());
            responseData.put("authorId", detailNoteData.getAuthorId());
            responseData.put("createdAt", detailNoteData.getCreatedAt());
            responseData.put("modifiedAt", detailNoteData.getModifiedAt());
            responseData.put("price", detailNoteData.getPrice());
            responseData.put("isBought", detailService.isBought(userId, noteId));
            responseData.put("bookInfo", bookInfo);

            response.put("data", responseData);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // 기타 예외가 발생한 경우
            Map<String, Object> responseBody = new LinkedHashMap<>();

            // 500
            Map<String, String> apiStatus = new HashMap<>();
            apiStatus.put("errorMessage", "서버 오류가 발생했습니다.");
            apiStatus.put("errorCode", "N500");
            responseBody.put("apiStatus", apiStatus);

            return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
