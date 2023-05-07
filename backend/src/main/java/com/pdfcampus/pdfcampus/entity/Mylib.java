package com.pdfcampus.pdfcampus.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mylib")
public class Mylib {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mid")
    private int mid;

    @Column(name = "uid")
    private int uid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nid")
    private Note note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bid")
    private Book book;

    public Mylib(int uid, Note note) {
        this.uid = uid;
        this.note = note;
    }

    public Mylib(int uid, Book book) {
        this.uid = uid;
        this.book = book;
    }
}
