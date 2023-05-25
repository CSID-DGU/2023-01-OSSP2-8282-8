package com.pdfcampus.pdfcampus.entity;


import javax.persistence.Entity;
import javax.persistence.Table;

import com.amazonaws.services.alexaforbusiness.model.Text;
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

    @Column(name = "page number")
    private Integer pageNumber;
}
