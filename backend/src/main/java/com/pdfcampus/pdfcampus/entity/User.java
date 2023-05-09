package com.pdfcampus.pdfcampus.entity;

import lombok.*;

import java.time.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer uid;

    @Column(name = "userid", nullable = false)
    private String userId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "isSubscribed", nullable = false)
    private boolean isSubscribed;

    @Column(name = "productName")
    private String productName;

    @Column(name = "subcribeDate")
    private LocalDate subscribeDate;

    @Column(name = "joinedDate")
    private LocalDate joinedDate;

    @Column(name = "refreshToken")
    private String refreshToken;
}
