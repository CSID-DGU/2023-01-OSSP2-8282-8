package com.pdfcampus.pdfcampus.service;

import com.pdfcampus.pdfcampus.dto.UserForm;
import com.pdfcampus.pdfcampus.repository.SignupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class SignupService {
    private SignupRepository signupRepository;

    @Transactional
    public int joinUser(UserForm userForm){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userForm.setPassword(passwordEncoder.encode(userForm.getPassword()));

        return signupRepository.save(userForm.toEntity()).getUid();
    }
}
