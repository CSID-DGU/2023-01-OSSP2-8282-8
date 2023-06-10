package com.pdfcampus.pdfcampus.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Sale")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nid", nullable = false)
    private Note note;

    @Column(name = "noteimage")
    private byte[] noteImage;

    @Column(name = "price")
    private Integer price;
}

