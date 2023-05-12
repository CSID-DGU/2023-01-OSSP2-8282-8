package com.pdfcampus.pdfcampus.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@ToString
@Setter
@Getter
public class DetailNoteDto {
    private Integer nid;
    private String noteTitle;
    private String noteAuthor;
    private LocalDate createdAt;
    private LocalDate modifiedAt;
    private String price;
    private boolean isBought;
    //private boolean isSale; // MylibController에서만 사용
    private String bookAuthor;
    private Integer authorId;
    private String publisher;
    private Integer publicationYear;
    private byte[] bookCover;
}
