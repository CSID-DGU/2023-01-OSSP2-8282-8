package com.pdfcampus.pdfcampus.service;

import com.pdfcampus.pdfcampus.dto.BookAddDto;
import com.pdfcampus.pdfcampus.dto.BookDto;
import com.pdfcampus.pdfcampus.entity.Book;
import com.pdfcampus.pdfcampus.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookAddService {
    private final BookRepository bookRepository;
    private final DetailService detailService;

    public BookAddService(BookRepository bookRepository, DetailService detailService){
        this.bookRepository = bookRepository;
        this.detailService = detailService;
    }
    public Book addBook(BookDto bookDto) {
        Book book = bookDto.toEntity();

        return bookRepository.save(book);
    }

    public boolean isDuplicated(BookAddDto addDto){
        // uid에 해당하는 mylib에서 bid에 해당하는 book이 store 되어있는지 확인. stroe 되어있다면 해당 bid는 중복되는 것임
        return detailService.isStored(Integer.toString(addDto.getUid()), Integer.toString(addDto.getBid()));
    }
}
