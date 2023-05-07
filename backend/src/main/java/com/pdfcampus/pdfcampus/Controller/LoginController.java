package com.pdfcampus.pdfcampus.controller;

import com.pdfcampus.pdfcampus.dto.LoginDTO;
//import com.pdfcampus.pdfcampus.entity.LoginEntity;
import com.pdfcampus.pdfcampus.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDTO loginDTO, @RequestHeader(value = "Content-Type", defaultValue = "application/json") String contentType) {
        boolean isLoginSuccess = loginService.login(loginDTO);
        if (isLoginSuccess) {
            Map<String, Object> response = new HashMap<>();
            Map<String, Object> data = new HashMap<>();
            data.put("accessToken", loginDTO.getAccessToken());
            data.put("refreshToken", loginDTO.getRefreshToken());
            data.put("userId", loginDTO.getUserId());
            data.put("isSubscribed", loginDTO.isSubscribed());
            response.put("data", data);
            response.put("apiStatus", null);
            return ResponseEntity.ok().body(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            Map<String, Object> apiStatus = new HashMap<>();
            apiStatus.put("errorCode", "E400");
            apiStatus.put("errorMessage", "로그인 정보 불일치");
            response.put("data", null);
            response.put("apiStatus", apiStatus);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}