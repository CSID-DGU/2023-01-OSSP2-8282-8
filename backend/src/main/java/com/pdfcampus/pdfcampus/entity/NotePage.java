package com.pdfcampus.pdfcampus.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "NotePage")
public class NotePage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "npid")
    private int npid;

    @Column(name = "nid")
    private int nid;

    @Column(name = "notepageNumber")
    private Integer notepageNumber;

    @Column(name = "notepageUrl", length = 1023)
    private String notepageUrl;
}
