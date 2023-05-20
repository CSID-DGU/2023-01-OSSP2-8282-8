package com.pdfcampus.pdfcampus.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.*;
import java.util.List;
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

    //@OneToMany(mappedBy = "note", fetch = FetchType.LAZY)
    //private List<Sale> sales; // Sale 엔티티와의 일대다 관계

    @Column(name = "noteTitle", nullable = false)
    private String noteTitle;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "createdAt", nullable = false)
    private LocalDate createdAt; // LocalDate 오직 날짜만 다룸

    @Column(name = "modifiedAt", nullable = false)
    private LocalDate modifiedAt;

    public Note(Integer nid, String noteTitle, String bookCover) {
    }

    //public boolean isSale() {
    //    return sales != null && !sales.isEmpty();
    //}
}
