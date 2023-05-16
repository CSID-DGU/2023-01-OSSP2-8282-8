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
            if(book.getBookTitle() == input.getBookTitle() &&
            book.getAuthor() == input.getAuthor() &&
            book.getPublisher() == input.getPublisher() &&
            book.getPublicationYear() == input.getPublicationYear() &&
            book.getBookCover() == input.getBookCover()) {
                return true;
            }

        }

        return false;
    }
}
