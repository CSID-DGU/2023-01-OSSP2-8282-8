package com.pdfcampus.pdfcampus.Controller;


import com.pdfcampus.pdfcampus.dto.SignupDto;
import com.pdfcampus.pdfcampus.entity.User;
import com.pdfcampus.pdfcampus.repository.SignupRepository;
import com.pdfcampus.pdfcampus.service.SignupService;
import com.pdfcampus.pdfcampus.util.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping
public class SignupController {
    private final SignupService signupService;
    private final JwtTokenProvider jwtTokenProvider;

    public SignupController(SignupService signupService, JwtTokenProvider jwtTokenProvider){
        this.signupService = signupService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public boolean existsByUserId(String userId) {
        return signupService.existsByUserId(userId);
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> signUp(@RequestBody SignupDto signupDto) {
        if(signupService.existsByUserId(signupDto.getId())) { // 중복되는 아이디
            Map<String, Object> apiStatus = new HashMap<>();
            apiStatus.put("errorMessage", "중복되는 아이디");
            apiStatus.put("errorCode", "E400");

            Map<String, Object> response = new HashMap<>();
            response.put("data", new HashMap<>());
            response.put("apiStatus", apiStatus);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        String accessToken = jwtTokenProvider.generateAccessToken(signupDto.toEntity());
        String refreshToken = jwtTokenProvider.generateRefreshToken(signupDto.toEntity());

        Map<String, Object> responseData = new LinkedHashMap<>();
        responseData.put("accessToken", accessToken);
        responseData.put("refreshToken", refreshToken);
        responseData.put("userId", signupDto.getUid()); //uid

        Map<String, Object> response = new HashMap<>();
        response.put("data", responseData);

        return ResponseEntity.ok(response);
    }
}


