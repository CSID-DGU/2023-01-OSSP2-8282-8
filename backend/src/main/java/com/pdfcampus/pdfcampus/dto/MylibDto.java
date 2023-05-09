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

    public MylibDto() {
        this.noteList = new ArrayList<>();
        this.bookList = new ArrayList<>();
    }

}
