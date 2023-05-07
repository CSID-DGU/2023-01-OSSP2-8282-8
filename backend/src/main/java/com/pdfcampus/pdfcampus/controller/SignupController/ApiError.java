package com.pdfcampus.pdfcampus.controller.SignupController;

public class ApiError {
    private String errorCode;
    private String errorMessage;

    public ApiError(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}