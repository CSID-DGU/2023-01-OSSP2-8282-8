package com.pdfcampus.pdfcampus.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter

public class MylibBookDto {
    private Integer bookId;
    private String bookTitle;
    private String bookCover;

    public MylibBookDto() {
    }
}
