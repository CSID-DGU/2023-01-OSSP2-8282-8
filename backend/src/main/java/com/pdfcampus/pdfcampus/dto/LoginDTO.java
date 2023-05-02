package com.pdfcampus.pdfcampus.dto;

public class LoginDTO {
    private String userid;
    private String password;
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "userid='" + userid + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
