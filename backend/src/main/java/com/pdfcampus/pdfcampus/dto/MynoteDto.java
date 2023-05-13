package com.pdfcampus.pdfcampus.dto;

import com.pdfcampus.pdfcampus.entity.Note;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MynoteDto {
    private int noteId;
    private String noteTitle;
    private LocalDate createdAt;
    private LocalDate modifiedAt;
    private boolean isSale;
    private BookDto bookInfo;
    private List<Note> noteList;

    public MynoteDto(Integer noteId, String noteTitle, LocalDate createdAt, LocalDate modifiedAt, boolean isSale, BookDto bookInfo) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.isSale = isSale;
        this.bookInfo = bookInfo;
    }

    public void setIsSale(boolean isSale) {
        this.isSale = isSale;
    }
    // setter 메서드
    public void setBookInfo(BookDto bookInfo) {
        this.bookInfo = bookInfo;
    }

}
