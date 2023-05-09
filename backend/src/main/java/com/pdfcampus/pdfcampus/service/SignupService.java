package com.pdfcampus.pdfcampus.service;

import com.pdfcampus.pdfcampus.dto.SignupDto;
import com.pdfcampus.pdfcampus.entity.User;
import com.pdfcampus.pdfcampus.repository.SignupRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SignupService {
    private final SignupRepository signupRepository;

    public SignupService(SignupRepository signupRepository) {
        this.signupRepository = signupRepository;
    }

    public boolean existsByUserId(String userId) {
        return signupRepository.existsByUserId(userId);
    }

    public User createUser(SignupDto signupDto) {
        User user = signupDto.toEntity();

        return signupRepository.save(user);
    }
}
