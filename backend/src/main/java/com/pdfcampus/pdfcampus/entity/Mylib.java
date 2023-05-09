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
    private Integer mid;

    @Column(name = "uid")
    private Integer uid;

    @Column(name = "nid")
    private Integer nid;

    @Column(name = "bid")
    private Integer bid;

    @Transient
    private Note note;

    @Transient
    private Book book;

    @PostLoad
    private void onLoad() {
        if (nid != null) {
            note = new Note();
            note.setNid(nid);
        }
        if (bid != null) {
            book = new Book();
            book.setBid(bid);
        }
    }

    @PrePersist
    private void onSave() {
        if (note != null) {
            nid = note.getNid();
        }
        if (book != null) {
            bid = book.getBid();
        }
    }

    public Mylib(Integer uid, Note note) {
        this.uid = uid;
        this.note = note;
    }

    public Mylib(Integer uid, Book book) {
        this.uid = uid;
        this.book = book;
    }
}
