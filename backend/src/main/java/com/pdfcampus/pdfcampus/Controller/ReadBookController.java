package com.pdfcampus.pdfcampus.Controller;
import com.pdfcampus.pdfcampus.dto.DetailBookDto;
import com.pdfcampus.pdfcampus.entity.Book;
import com.pdfcampus.pdfcampus.entity.Note;
import com.pdfcampus.pdfcampus.service.ReadBookService;
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
public class ReadBookController {

    @Autowired
    private ReadBookService readBookService;

    @GetMapping("/read/book/{bookId}")
    public ResponseEntity<Map<String, Object>> getBook(@PathVariable String bookId) {
        try {
            System.out.print("Controller: "+bookId+"\n");
            DetailBookDto book = readBookService.getBookById(bookId);
            System.out.print(book.getBid());
            Map<String, Object> response = new HashMap<>();
            Map<String, Object> data = new HashMap<>();
            List<byte[]> pages = new ArrayList<>();
            List<Note> notes = new ArrayList<>();

            // Here you should populate pages and notes according to your business logic
            // This may involve additional service methods and repository queries

            data.put("pages", pages);
            data.put("notes", notes);
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
