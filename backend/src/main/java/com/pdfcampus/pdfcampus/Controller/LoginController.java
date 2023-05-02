package com.pdfcampus.pdfcampus.Controller;

import com.pdfcampus.pdfcampus.dto.LoginDTO;
import com.pdfcampus.pdfcampus.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDTO loginDTO) {
        boolean isLoginSuccess = loginService.login(loginDTO);
        if (isLoginSuccess) {
            return ResponseEntity.ok(loginDTO); //성공시 200
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); //실패시 401
        }
    }
}