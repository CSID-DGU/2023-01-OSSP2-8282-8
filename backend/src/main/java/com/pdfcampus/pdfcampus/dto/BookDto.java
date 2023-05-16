package com.pdfcampus.pdfcampus.dto;

import com.pdfcampus.pdfcampus.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@ToString
@Setter
@Getter
public class BookDto {
    private int bid;
    private String bookTitle;
    private String author;
    private String publisher;
    private int publicationYear;
    private String bookCover;
    private boolean isStored;

    private List<BookDto> bookdtoList;

    public BookDto() {
        this.bookdtoList = new ArrayList<>();
    }

    public BookDto(String author, String publisher, int publicationYear, byte[] bookCover) {
    }

    public Book toEntity(){
        return new Book(bid, bookTitle, author, publisher, publicationYear, bookCover.getBytes());
    }
}
