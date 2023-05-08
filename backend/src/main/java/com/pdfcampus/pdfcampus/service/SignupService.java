package com.pdfcampus.pdfcampus.service;

import com.pdfcampus.pdfcampus.dto.SignupDto;
import com.pdfcampus.pdfcampus.entity.User;
import com.pdfcampus.pdfcampus.repository.SignupRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class SignupService {
    private final SignupRepository signupRepository;
    private final PasswordEncoder passwordEncoder;

    public SignupService(SignupRepository signupRepository, PasswordEncoder passwordEncoder) {
        this.signupRepository = signupRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean existsByUserId(String userId) {
        return signupRepository.existsByUserId(userId);
    }

    public User createUser(SignupDto signupDto) {
        User user = new User();
        user.setUserId(signupDto.getId());
        user.setUsername(signupDto.getUsername());
        user.setPassword(passwordEncoder.encode(signupDto.getPassword()));

        return signupRepository.save(user);
    }
}
