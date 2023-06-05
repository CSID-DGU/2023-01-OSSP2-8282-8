package com.pdfcampus.pdfcampus.entity;


import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Page")
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;

    @Column(name = "bid", nullable = false)
    private Integer bid;

    @Column(name = "nid", nullable = false)
    private Integer nid;

    @Column(name = "pageNumber")
    private Integer pageNumber;

    @Column(name = "pageUrl")
    private String pageUrl;
}
