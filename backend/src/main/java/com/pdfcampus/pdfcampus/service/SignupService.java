package com.pdfcampus.pdfcampus.service;

import com.pdfcampus.pdfcampus.dto.SignupDto;
import com.pdfcampus.pdfcampus.repository.SignupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class SignupService {
    private SignupRepository signupRepository;

    @Transactional
    public boolean joinUser(SignupDto signupDto){ //데이터베이스에 회원정보를 저장하는 과정
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        signupDto.setPassword(passwordEncoder.encode(signupDto.getPassword())); // 비밀번호를 salt값과 같이 저장, 동일 비밀번호에 대한 노출 위험 방지

        signupRepository.save(signupDto.toEntity());

        return true;
    }

    public boolean isUserIdDuplicated(String userId) { //아이디 중복검사
        return signupRepository.findByUserId(userId).isPresent();
    }
}
