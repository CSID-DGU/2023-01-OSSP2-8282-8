package com.pdfcampus.pdfcampus.service;

import com.pdfcampus.pdfcampus.dto.BookDto;
import com.pdfcampus.pdfcampus.dto.SignupDto;
import com.pdfcampus.pdfcampus.entity.Book;
import com.pdfcampus.pdfcampus.entity.User;
import com.pdfcampus.pdfcampus.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookAddService {
    private final BookRepository bookRepository;

    public BookAddService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    public Book createUser(BookDto bookDto) {

    }
}
