package com.pdfcampus.pdfcampus.entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    private int bid;

    private String bookTitle;

    private String author;

    private String publisher;

    private int publicationYear;

    @Lob
    private byte[] bookCover;
}