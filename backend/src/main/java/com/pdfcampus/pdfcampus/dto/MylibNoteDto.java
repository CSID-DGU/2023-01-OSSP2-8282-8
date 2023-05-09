package com.pdfcampus.pdfcampus.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class MylibNoteDto {
    private Integer noteId;
    private String noteTitle;
    private byte[] bookCover;

    public MylibNoteDto() {
    }
}
