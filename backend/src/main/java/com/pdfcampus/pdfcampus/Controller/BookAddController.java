package com.pdfcampus.pdfcampus.Controller;

import com.pdfcampus.pdfcampus.dto.BookAddDto;
import com.pdfcampus.pdfcampus.dto.BookDto;
import com.pdfcampus.pdfcampus.service.BookAddService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping
public class BookAddController {
    private final BookAddService bookAddService;

    public BookAddController(BookAddService bookAddService){
        this.bookAddService = bookAddService;
    }

    @PostMapping("/book/add")
    public ResponseEntity<Map<String, Object>> bookAdd(@RequestBody BookAddDto addDto) {
        Map<String, Object> apiStatus = new HashMap<>();

        if(bookAddService.isDuplicated(addDto)) { // 중복되는 도서
            apiStatus.put("errorMessage", "이미 존재하는 도서");
            apiStatus.put("errorCode", "E400");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiStatus);
        }

        bookAddService.addBook(bookDto);

        apiStatus.put("errorMessage", "추가 완료");
        apiStatus.put("errorCode", "N200");

        return ResponseEntity.ok(apiStatus);
    }
}
