package com.pdfcampus.pdfcampus.entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Mylib_item")
public class MylibItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lid")
    private Mylib mylib;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nid")
    private Note note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bid")
    private Book book;
}
