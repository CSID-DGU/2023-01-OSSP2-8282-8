package com.pdfcampus.pdfcampus.Controller;
import com.pdfcampus.pdfcampus.dto.DetailNoteDto;
import com.pdfcampus.pdfcampus.entity.Note;
import com.pdfcampus.pdfcampus.service.ReadNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class ReadNoteController {

    @Autowired
    private ReadNoteService readNoteService;

    @GetMapping("/read/note/{noteId}")
    public ResponseEntity<Map<String, Object>> getBook(@PathVariable String noteId) {
        try {
            Map<String, Object> response = new HashMap<>();
            Map<String, Object> data = new HashMap<>();

            //String bookCoverUrl = readBookService.getBookCoverUrl(bookId).toString();
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
