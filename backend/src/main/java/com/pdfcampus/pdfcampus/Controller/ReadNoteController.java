package com.pdfcampus.pdfcampus.Controller;
import com.pdfcampus.pdfcampus.dto.DetailNoteDto;
import com.pdfcampus.pdfcampus.entity.Note;
import com.pdfcampus.pdfcampus.service.DetailService;
import com.pdfcampus.pdfcampus.service.LoginService;
import com.pdfcampus.pdfcampus.service.ReadNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping
public class ReadNoteController {

    @Autowired
    private ReadNoteService readNoteService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private DetailService detailService;

    @GetMapping("/read/note")
    public ResponseEntity<Map<String, Object>> getBook(@RequestParam("userId") String userId, @RequestParam("noteId") String noteId) {
        try {
            Map<String, Object> response = new HashMap<>();
            Map<String, Object> data = new LinkedHashMap<>();

            if(readNoteService.isUserSubscribed(userId)) { //만약 사용자가 구독을 했다면 book도 같이 전송
                String bookId = readNoteService.getBookId(noteId);
                String bookPDFUrl = readNoteService.getBookPdfUrl(bookId).toString();
                data.put("bookPDFUrl", bookPDFUrl);
            }

            String notePDFUrl = readNoteService.getNotePdfUrl(noteId).toString();
            data.put("notePDFUrl", notePDFUrl);

            response.put("data", data);

            Map<String, String> apiStatus = new HashMap<>();
            apiStatus.put("errorMessage", "");
            apiStatus.put("errorCode", "N200");
            response.put("apiStatus", apiStatus);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            Map<String, String> apiStatus = new HashMap<>();
            apiStatus.put("errorMessage", e.getMessage());
            apiStatus.put("errorCode", "E500");
            errorResponse.put("apiStatus", apiStatus);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}