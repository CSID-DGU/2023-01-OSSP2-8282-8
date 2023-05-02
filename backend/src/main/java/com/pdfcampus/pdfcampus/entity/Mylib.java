package com.pdfcampus.pdfcampus.entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Mylib")
public class Mylib {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid")
    private User user;

}
