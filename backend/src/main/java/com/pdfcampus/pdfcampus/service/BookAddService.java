package com.pdfcampus.pdfcampus.service;

import com.pdfcampus.pdfcampus.dto.BookDto;
import com.pdfcampus.pdfcampus.entity.Book;
import com.pdfcampus.pdfcampus.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookAddService {
    private final BookRepository bookRepository;

    public BookAddService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    public Book addBook(BookDto bookDto) {
        Book book = bookDto.toEntity();

        return bookRepository.save(book);
    }

    public boolean isDuplicated(BookDto bookDto){
        List<Book> bookList = bookRepository.findByBookTitle(bookDto.getBookTitle());
        Book input = bookDto.toEntity();
        for (Book book : bookList) {
            if(book.getBookTitle().equals(input.getBookTitle()) &&
            book.getAuthor().equals(input.getAuthor()) &&
            book.getPublisher().equals(input.getPublisher()) &&
            book.getPublicationYear().equals(input.getPublicationYear())){
                return true;
            }

        }

        return false;
    }
}
