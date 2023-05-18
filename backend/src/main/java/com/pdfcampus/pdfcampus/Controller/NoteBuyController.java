package com.pdfcampus.pdfcampus.Controller;

import com.pdfcampus.pdfcampus.dto.DetailNoteDto;
import com.pdfcampus.pdfcampus.entity.Note;
import com.pdfcampus.pdfcampus.service.DetailService;
import com.pdfcampus.pdfcampus.service.NoteBuyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping
public class NoteBuyController {

    private final NoteBuyService noteBuyService;
    private final DetailService detailService;

    public NoteBuyController(NoteBuyService noteBuyService, DetailService detailService){
        this.noteBuyService = noteBuyService;
        this.detailService = detailService;
    }

    @PostMapping("/note/buy")
    public ResponseEntity<Map<String, Object>> buyNote(@RequestBody Map<String, Integer> request){
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            String userId = String.valueOf(request.get("userId"));
            Integer noteId = request.get("noteId");
            noteBuyService.buyNote(Integer.valueOf(userId), noteId);
            DetailNoteDto detailNoteData = detailService.getNoteData(String.valueOf(noteId));

            Map<String, Object> responseData = new LinkedHashMap<>();
            Map<String, Object> bookInfo = new LinkedHashMap<>();
            Map<String, String> apiStatus = new HashMap<>();

            bookInfo.put("author", detailNoteData.getBookAuthor());
            bookInfo.put("publisher", detailNoteData.getPublisher());
            bookInfo.put("publicationYear", detailNoteData.getPublicationYear());
            bookInfo.put("bookCover", detailNoteData.getBookCover());

            responseData.put("noteTitle", detailNoteData.getNoteTitle());
            responseData.put("author", detailNoteData.getNoteAuthor());
            responseData.put("authorId", detailNoteData.getAuthorId());
            responseData.put("createdAt", detailNoteData.getCreatedAt());
            responseData.put("modifiedAt", detailNoteData.getModifiedAt());
            responseData.put("price", detailNoteData.getPrice());
            responseData.put("isBought", detailService.isBought(userId, String.valueOf(noteId)));
            responseData.put("bookInfo", bookInfo);

            response.put("data", responseData);

            apiStatus.put("errorMessage", "");
            apiStatus.put("errorCode", "N200");
            response.put("apiStatus", apiStatus);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> responseBody = new LinkedHashMap<>();

            Map<String, String> apiStatus = new HashMap<>();
            // 판매하지 않은 노트를 mylib에 넣으려고 하는 경우, 오류 발생
            apiStatus.put("errorMessage", "서버 오류가 발생했습니다."+ e.getMessage());
            apiStatus.put("errorCode", "N500");
            responseBody.put("apiStatus", apiStatus);
            return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
