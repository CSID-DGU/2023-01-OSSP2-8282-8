package com.pdfcampus.pdfcampus.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bid;

    @Column(name = "bookTitle", nullable = false)
    private String bookTitle;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "publisher", nullable = false)
    private String publisher;

    @Column(name = "publicationYear", nullable = false)
    private Integer publicationYear;

    @Column(name = "bookCover")
    @Lob
    private String bookCover;

}
