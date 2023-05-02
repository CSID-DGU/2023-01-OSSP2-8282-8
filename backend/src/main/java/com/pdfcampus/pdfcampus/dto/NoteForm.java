package com.pdfcampus.pdfcampus.dto;

import com.pdfcampus.pdfcampus.entity.User;
import com.pdfcampus.pdfcampus.entity.Book;
import lombok.AllArgsConstructor;
import lombok.ToString;
import java.time.LocalDate;

@AllArgsConstructor
@ToString
public class NoteForm {

    private int nid;
    private User user;
    private Book book;
    private String noteTitle;
    private String author;
    private LocalDate createdAt;
    private LocalDate modifiedAt;
    private int price;

    public NoteForm toEntity(){
        return new NoteForm(nid, user, book, noteTitle, author, createdAt, modifiedAt, price);
    }
}
