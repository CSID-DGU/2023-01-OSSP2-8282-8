package com.pdfcampus.pdfcampus.dto;

import com.pdfcampus.pdfcampus.entity.User;
import com.pdfcampus.pdfcampus.entity.Note;
import com.pdfcampus.pdfcampus.entity.Book;
import com.pdfcampus.pdfcampus.entity.Mylib;
import com.pdfcampus.pdfcampus.entity.MylibItem;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class MylibItemForm {
    private Integer mid;
    private Mylib mylib;
    private Note note;
    private Book book;

    public MylibItem toEntity(){
        return new MylibItem(mid, mylib, note, book);
    }
}
