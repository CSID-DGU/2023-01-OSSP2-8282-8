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
    private int mid;
    private int uid; //userId임에 주의
    private int nid;
    private int bid;

    private List<MylibNoteDto> noteList;
    private List<MylibBookDto> bookList;
    private Boolean isNoteMore;
    private Boolean isBookMore;

    public MylibDto() {
        this.noteList = new ArrayList<>();
        this.bookList = new ArrayList<>();
    }
    public void setNoteList(List<MylibNoteDto> noteList) {
        if (noteList.size() > 5) {
            this.noteList = noteList.subList(0, 5);
            this.isNoteMore = true;
        } else {
            this.noteList = noteList;
            this.isNoteMore = false;
        }
    }

    public void setBookList(List<MylibBookDto> bookList) {
        if (bookList.size() > 5) {
            this.bookList = bookList.subList(0, 5);
            this.isBookMore = true;
        } else {
            this.bookList = bookList;
            this.isBookMore = false;
        }
    }

}
