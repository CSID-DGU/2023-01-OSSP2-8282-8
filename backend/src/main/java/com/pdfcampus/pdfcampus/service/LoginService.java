package com.pdfcampus.pdfcampus.service;

import com.pdfcampus.pdfcampus.entity.User;
import com.pdfcampus.pdfcampus.repository.LoginRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
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
        User user = loginRepository.findByUserId(id);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        } else {
            assert user != null;
            System.out.print(user.getPassword());
            return false;
        }
    }

    public Map<String, String> issueTokens(String id) {
        Optional<User> optionalUser = Optional.ofNullable(loginRepository.findByUserId(String.valueOf(id)));
        User userObj = optionalUser.orElse(null);

        if (userObj != null) {
            Map<String, String> tokens = new HashMap<>();

            Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

            String accessToken = Jwts.builder()
                    .setSubject(userObj.getUserId())
                    .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1)))
                    .signWith(key)
                    .compact();

            String refreshToken = Jwts.builder()
                    .setSubject(userObj.getUserId())
                    .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(7)))
                    .signWith(key)
                    .compact();

            tokens.put("accessToken", accessToken);
            tokens.put("refreshToken", refreshToken);

            return tokens;
        }

        return null;
    }
    public int getUid(String id) {
        User user = loginRepository.findByUserId(id);
        if (user != null) {
            return user.getUid();
        } else {
            throw new IllegalArgumentException("User not found"); // 예외 처리
        }
    }


    public boolean isUserSubscribed(String id) {
        User user = loginRepository.findByUserId(id);
        if (user != null) {
            return user.isSubscribed();
        } else {
            return false; // 예외 처리
        }
    }

}
