package com.pdfcampus.pdfcampus.dto;

import java.time.LocalDate;

public class DetailNoteDto {
    private Integer nid;
    private String noteTitle;
    private LocalDate createdAt;
    private LocalDate modifiedAt;
    private String price;
    private boolean isBought;
    private String author;
    private Integer authorId;
    private String publisher;
    private Integer publicationYear;
    private byte[] bookCover;
}
