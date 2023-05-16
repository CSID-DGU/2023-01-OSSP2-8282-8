package com.pdfcampus.pdfcampus.Controller;

import com.pdfcampus.pdfcampus.dto.BookDto;
import com.pdfcampus.pdfcampus.dto.SignupDto;
import com.pdfcampus.pdfcampus.service.BookAddService;
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
public class BookAddController {
    private final BookAddService bookAddService;

    public BookAddController(BookAddService bookAddService){
        this.bookAddService = bookAddService;
    }

    @PostMapping("/book/add")
    public ResponseEntity<Map<String, Object>> signUp(@RequestBody BookDto bookDto) {

    }
}
