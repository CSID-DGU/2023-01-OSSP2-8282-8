package com.pdfcampus.pdfcampus.Controller;

import com.pdfcampus.pdfcampus.dto.DetailBookDto;
import com.pdfcampus.pdfcampus.dto.DetailNoteDto;
import com.pdfcampus.pdfcampus.dto.MylibBookDto;
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
public class MylibController {
    private final MylibService mylibService;
    private final DetailService detailService;

    public MylibController(MylibService mylibService, DetailService detailService) {
        this.mylibService = mylibService;
        this.detailService = detailService;
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
            dataMap.put("isNoteMore", mylibData.getIsNoteMore()); // note 개수 5개 초과
            dataMap.put("isBookMore", mylibData.getIsBookMore()); // book 개수 5개 초과
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
            responseBody.putIfAbsent("data", Map.of("noteList", List.of(), "bookList", List.of(), "isNoteMore", false, "isBookMore", false));

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

            // 각각의 MylibBookDto 객체에 대한 DetailBookDto 정보를 구해서 Map에 추가
            List<Map<String, Object>> bookDataList = new ArrayList<>();
            for(MylibBookDto book : bookList) {
                Map<String, Object> bookData = new LinkedHashMap<>();
                DetailBookDto detailBookData = detailService.getBookData(String.valueOf(book.getBookId()));
                bookData.put("bookId", book.getBookId());
                bookData.put("bookTitle", detailBookData.getBookTitle());
                bookData.put("author", detailBookData.getAuthor());
                bookData.put("publisher", detailBookData.getPublisher());
                bookData.put("publicationYear", detailBookData.getPublicationYear());
                bookData.put("bookCover", detailBookData.getBookCover());
                bookDataList.add(bookData);
            }

            responseBody.put("data", Map.of("bookList", bookDataList));

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

            // 각각의 MylibNoteDto 객체에 대한 DetailNoteDto 정보를 구해서 Map에 추가
            List<Map<String, Object>> noteDataList = new ArrayList<>();
            for(MylibNoteDto note : noteList) {
                Map<String, Object> noteData = new LinkedHashMap<>();

                DetailNoteDto detailNoteData = detailService.getNoteData(String.valueOf(note.getNoteId()));
                noteData.put("noteId", note.getNoteId());
                noteData.put("noteTitle", detailNoteData.getNoteTitle());
                noteData.put("author", detailNoteData.getNoteAuthor());
                noteData.put("createdAt", detailNoteData.getCreatedAt());
                noteData.put("modifiedAt", detailNoteData.getModifiedAt());
                noteData.put("isSale", false);
                // bookInfo
                Map<String, Object> bookInfo = new LinkedHashMap<>();
                bookInfo.put("author", detailNoteData.getBookAuthor());
                bookInfo.put("publisher", detailNoteData.getPublisher());
                bookInfo.put("publicationYear", detailNoteData.getPublicationYear());
                bookInfo.put("bookCover", detailNoteData.getBookCover());
                noteData.put("bookInfo", bookInfo);
                noteDataList.add(noteData);
            }

            responseBody.put("data", Map.of("noteList", noteDataList));

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
