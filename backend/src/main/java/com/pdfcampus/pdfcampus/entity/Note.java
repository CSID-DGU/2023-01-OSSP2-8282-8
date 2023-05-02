package com.pdfcampus.pdfcampus.entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Note")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nid")
    private int nid;

    @JoinColumn(name = "uid")
    private User user;

    @JoinColumn(name = "bid")
    private Book book;

    @Column(name = "noteTitle")
    private String noteTitle;

    @Column(name = "author")
    private String author;

    @Column(name = "createdAt")
    private LocalDate createdAt;

    @Column(name = "modifiedAt")
    private LocalDate modifiedAt;

    @Column(name = "price")
    private int price;

}
