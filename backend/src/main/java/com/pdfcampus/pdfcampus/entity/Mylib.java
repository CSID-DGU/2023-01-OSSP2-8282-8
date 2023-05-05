package com.pdfcampus.pdfcampus.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Mylib")
public class Mylib {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid", nullable = false)
    private User user;

    @OneToMany(mappedBy = "mylib", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Mylib_item> mylibItems = new HashSet<>();
}
