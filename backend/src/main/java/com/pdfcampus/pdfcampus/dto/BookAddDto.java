package com.pdfcampus.pdfcampus.dto;

import com.pdfcampus.pdfcampus.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Setter
@Getter
public class BookAddDto {
    private int bid;
    private int uid;
    private String bookTitle;
    private String author;
    private String publisher;
    private int publicationYear;
    private String bookCover;
    private boolean isStored;

    public Book toEntity(){
        return new Book(bid, bookTitle, author, publisher, publicationYear, bookCover.getBytes());
    }
}
