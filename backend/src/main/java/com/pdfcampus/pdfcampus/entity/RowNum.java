package com.pdfcampus.pdfcampus.entity;

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
@Table(name = "RowNum")
public class RowNum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rid;

    @Column(name = "pid", nullable = false)
    private Integer pid;

    @Column(name = "rowNumber", nullable = false)
    private Integer rowNumber;

    @Column(name = "rowY", nullable = false)
    private Float rowY;

}
