package com.pdfcampus.pdfcampus.dto;

import com.pdfcampus.pdfcampus.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@ToString
@Setter
@Getter
public class SignupDto {
    private int uid;
    private String id;
    private String password;
    private String username;
    private String refreshToken;

    public User toEntity() {
        return new User(uid, id, password, username, false, null, null, LocalDate.now(), refreshToken);
    }
}
