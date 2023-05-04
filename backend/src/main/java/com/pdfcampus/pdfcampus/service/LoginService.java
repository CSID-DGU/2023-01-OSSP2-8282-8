package com.pdfcampus.pdfcampus.service;

import com.pdfcampus.pdfcampus.entity.User;
import com.pdfcampus.pdfcampus.repository.LoginRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class LoginService {
    private final LoginRepository loginRepository;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Autowired
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public boolean checkLogin(String id, String password) {
        if (id == null || id.isEmpty()) { // id가 null 또는 빈 문자열인 경우 처리
            return false;
        }
        try {
            Integer userId = Integer.parseInt(id); // id를 Integer형으로 변환
            Optional<User> optionalUser = loginRepository.findById(userId);
            User userObj = optionalUser.orElse(null);
            return userObj != null && BCrypt.checkpw(password, userObj.getPassword());
        } catch (NumberFormatException e) { // id가 숫자가 아닌 경우 처리
            return false;
        }
    }

    public Map<String, String> issueTokens(String id) {
        Optional<User> optionalUser = loginRepository.findById(Integer.valueOf(id));
        User userObj = optionalUser.orElse(null);

        if (userObj != null) {
            Map<String, String> tokens = new HashMap<>();

            String accessToken = Jwts.builder()
                    .setSubject(userObj.getUserId())
                    .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1)))
                    .signWith(SignatureAlgorithm.HS512, jwtSecret)
                    .compact();

            String refreshToken = Jwts.builder()
                    .setSubject(userObj.getUserId())
                    .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(7)))
                    .signWith(SignatureAlgorithm.HS512, jwtSecret)
                    .compact();

            tokens.put("accessToken", accessToken);
            tokens.put("refreshToken", refreshToken);

            return tokens;
        }

        return null;
    }
}
