package com.pdfcampus.pdfcampus.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "note")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bid", nullable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid", nullable = false)
    private User user;

    @Column(name = "noteTitle", nullable = false)
    private String noteTitle;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "createdAt", nullable = false)
    private LocalDate createdAt; // LocalDate 오직 날짜만 다룸

    @Column(name = "modifiedAt", nullable = false)
    private LocalDate modifiedAt;
}
