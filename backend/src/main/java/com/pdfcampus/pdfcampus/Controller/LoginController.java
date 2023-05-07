package com.pdfcampus.pdfcampus.Controller;

import com.pdfcampus.pdfcampus.dto.LoginDto;
import com.pdfcampus.pdfcampus.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginDto loginDto) {
        // 로그인 정보를 검증하고 결과를 반환하는 로직
        boolean isLoginValid = loginService.checkLogin(loginDto.getId(), loginDto.getPassword());
        if (isLoginValid) {
            // 로그인 정보가 올바르면 accessToken과 refreshToken을 발급받아 반환
            Map<String, String> tokens = loginService.issueTokens(loginDto.getId());
            String accessToken = tokens.get("accessToken");
            String refreshToken = tokens.get("refreshToken");

            Map<String, Object> responseData = new LinkedHashMap<>();
            responseData.put("accessToken", accessToken);
            responseData.put("refreshToken", refreshToken);
            responseData.put("userId", loginService.getUid(loginDto.getId())); //uid
            responseData.put("isSubscribed", loginService.isUserSubscribed(loginDto.getId()));


            Map<String, Object> response = new HashMap<>();
            response.put("data", responseData);

            return ResponseEntity.ok(response);
        } else {
            // 로그인 정보가 올바르지 않으면 에러 메시지를 반환
            Map<String, Object> apiStatus = new HashMap<>();
            apiStatus.put("errorMessage", "로그인 정보 불일치");
            apiStatus.put("errorCode", "E400");

            Map<String, Object> response = new HashMap<>();
            response.put("data", new HashMap<>());
            response.put("apiStatus", apiStatus);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
