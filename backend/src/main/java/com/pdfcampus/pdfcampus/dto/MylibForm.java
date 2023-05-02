package com.pdfcampus.pdfcampus.dto;

import com.pdfcampus.pdfcampus.entity.User;
import com.pdfcampus.pdfcampus.entity.Mylib;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class MylibForm {

    private Integer lid;
    private User user;

    public Mylib toEntity() {
        return new Mylib(lid, user);
    }
}