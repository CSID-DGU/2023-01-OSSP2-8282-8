package com.pdfcampus.pdfcampus.dto;

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
}
