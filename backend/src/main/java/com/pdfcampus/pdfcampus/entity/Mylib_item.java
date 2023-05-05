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
@Table(name = "Mylib_item")
public class Mylib_item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lid", nullable = false)
    private Mylib mylib;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bid")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nid")
    private Note note;
}
