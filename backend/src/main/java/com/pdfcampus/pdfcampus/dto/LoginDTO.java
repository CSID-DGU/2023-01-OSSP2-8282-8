package com.pdfcampus.pdfcampus.dto;

public class LoginDTO {
    private String userId;
    private String password;
    private String accessToken;
    private String refreshToken;
    private boolean isSubscribed;

    public LoginDTO() {}

    public LoginDTO(String userId, String password, String accessToken, String refreshToken, boolean isSubscribed) {
        this.userId = userId;
        this.password = password;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.isSubscribed = isSubscribed;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public boolean isSubscribed() {
        return isSubscribed;
    }

    public void setSubscribed(boolean subscribed) {
        isSubscribed = subscribed;
    }

}
