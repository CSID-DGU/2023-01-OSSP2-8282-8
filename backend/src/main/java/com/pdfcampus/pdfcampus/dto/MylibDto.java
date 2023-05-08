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
public class MylibDto {
    private Integer mid;
    private int uid; //userId임에 주의
    private List<MylibNoteDto> noteList;
    private List<MylibBookDto> bookList;

    public MylibDto() {
        this.noteList = new ArrayList<>();
        this.bookList = new ArrayList<>();
    }

    public static class MylibNoteDto {
        private Integer noteId;
        private String noteTitle;
        private byte[] bookCover;

        public MylibNoteDto(Integer noteId, String noteTitle, byte[] bookCover) {
            this.noteId = noteId;
            this.noteTitle = noteTitle;
            this.bookCover = bookCover;
        }
    }

    public static class MylibBookDto {
        private Integer bookId;
        private String bookTitle;
        private byte[] bookCover;

        public MylibBookDto(Integer bookId, String bookTitle, byte[] bookCover) {
            this.bookId = bookId;
            this.bookTitle = bookTitle;
            this.bookCover = bookCover;
        }
    }

}
