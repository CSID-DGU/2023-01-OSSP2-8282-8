package com.pdfcampus.pdfcampus.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Setter
@Getter
public class DetailBookDto {
    private int bid;
    private String bookTitle;
    private String author;
    private String publisher;
    private Integer publicationYear;
    private String bookCover; // URL of the book cover image
    private boolean isStored;

}
