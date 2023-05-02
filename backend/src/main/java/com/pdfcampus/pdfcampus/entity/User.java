package com.pdfcampus.pdfcampus.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@AllArgsConstructor //모든 필드를 매개변수로 받는 생성자 자동으로 생성
@NoArgsConstructor //매개변수가 없는 디폴트 생성자를 자동으로 생성
@Table(name = "User")
public class User {
    @Id //기본키 매핑
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 생성
    private int uid;

    @Column(name = "userid", nullable = false)
    private String userid;

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
