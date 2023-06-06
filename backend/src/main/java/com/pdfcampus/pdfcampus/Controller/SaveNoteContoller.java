package com.pdfcampus.pdfcampus.Controller;

import com.pdfcampus.pdfcampus.service.SaveNoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping
public class SaveNoteController {
    private final SaveNoteService saveNoteService;

    public SaveNoteController(SaveNoteService saveNoteService) {
        this.saveNoteService = saveNoteService;
    }

    @PostMapping("/note/save")
    public ResponseEntity<Map<String, Object>> saveNote(@RequestBody Map<String, Object> request) {
        Map<String, Object> note = (Map<String, Object>)request.get("note");
        Long userId = ((Number)request.get("userId")).longValue();
        Long bookId = ((Number)request.get("bookId")).longValue();

        boolean isNoteSaved = saveNoteService.saveNote(userId, bookId, note);

        Map<String, Object> apiStatus = new HashMap<>();
        if(isNoteSaved) {
            apiStatus.put("errorMessage", "저장이 완료되었습니다.");
            apiStatus.put("errorCode", "N200");
        } else {
            apiStatus.put("errorMessage", "노트 저장 실패");
            apiStatus.put("errorCode", "N400");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("apiStatus", apiStatus);

        return ResponseEntity.status(isNoteSaved ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(response);
    }
}
