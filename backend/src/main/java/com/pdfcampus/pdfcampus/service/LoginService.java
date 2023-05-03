package com.pdfcampus.pdfcampus.service;

import com.pdfcampus.pdfcampus.dto.LoginDTO;
import com.pdfcampus.pdfcampus.repository.LoginRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public boolean login(LoginDTO loginDTO) {
        // 로그인 처리를 수행하고 결과를 반환
        return true;
    }
}