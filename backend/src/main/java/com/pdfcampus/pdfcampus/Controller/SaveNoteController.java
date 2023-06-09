package com.pdfcampus.pdfcampus.Controller;

import com.amazonaws.services.s3.AmazonS3;
import com.pdfcampus.pdfcampus.service.SaveNoteService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/note")
public class SaveNoteController {
    private final SaveNoteService saveNoteService;
    private AmazonS3 s3client;

    public SaveNoteController(SaveNoteService saveNoteService, AmazonS3 s3client) {
        this.saveNoteService = saveNoteService;
        this.s3client = s3client;
    }

    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> saveNote(@RequestBody Map<String, Object> request) {
        String userId = (String) request.get("userId");
        String bookId = (String) request.get("bookId");
        Map<String, String> notes = (Map<String, String>) request.get("note");

        if (userId != null && !userId.isEmpty() && bookId != null && !bookId.isEmpty()) {
            boolean isNoteSaved = saveNoteService.saveNote(userId, bookId, notes);

            Map<String, Object> apiStatus = new HashMap<>();
            if (isNoteSaved) {
                apiStatus.put("errorMessage", "노트 저장이 완료되었습니다.");
                apiStatus.put("errorCode", "N200");
            } else {
                apiStatus.put("errorMessage", "노트 저장이 실패했습니다.");
                apiStatus.put("errorCode", "N400");
            }

            Map<String, Object> response = new HashMap<>();
            response.put("apiStatus", apiStatus);

            return ResponseEntity.status(isNoteSaved ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(response);
        } else {
            Map<String, Object> apiStatus = new HashMap<>();
            apiStatus.put("errorMessage", "요청 데이터가 올바르지 않습니다.");
            apiStatus.put("errorCode", "N400");

            Map<String, Object> response = new HashMap<>();
            response.put("apiStatus", apiStatus);

            return ResponseEntity.badRequest().body(response);
        }
    }
}




