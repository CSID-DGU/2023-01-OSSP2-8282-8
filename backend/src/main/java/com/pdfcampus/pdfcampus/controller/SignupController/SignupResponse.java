package com.pdfcampus.pdfcampus.Controller.SignupController;

public class SignupResponse {
    private String accessToken;
    private String refreshToken;
    private String userId;
    private ApiError apiStatus;

    public SignupResponse(String accessToken, String refreshToken, String userId) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.userId = userId;
        this.apiStatus = null;
    }

    public SignupResponse(String accessToken, String refreshToken, String userId, ApiError apiStatus) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.userId = userId;
        this.apiStatus = apiStatus;
    }

}