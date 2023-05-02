package com.pdfcampus.pdfcampus.dto;

import com.pdfcampus.pdfcampus.entity.Book;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class BookForm {

    private int bid;
    private String bookTitle;
    private String author;
    private String publisher;
    private int publicationYear;
    private byte[] bookCover;

    public Book toEntity() {
        return new Book(bid, bookTitle, author, publisher, publicationYear, bookCover);
    }
}

