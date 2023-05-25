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
@ToString
@Getter
@Setter
@AllArgsConstructor //모든 필드를 매개변수로 받는 생성자 자동으로 생성
@NoArgsConstructor //매개변수가 없는 디폴트 생성자를 자동으로 생성
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;

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

    @Column(name = "subcribeDate") // 스펠링 올바르게 고치면 오류 생김, run은 정상적으로 작동
    private LocalDate subscribeDate;

    @Column(name = "joinedDate")
    private LocalDate joinedDate;

    @Column(name = "refreshToken")
    private String refreshToken;

}
