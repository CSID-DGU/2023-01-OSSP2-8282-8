package com.pdfcampus.pdfcampus.util.jwt;

import com.pdfcampus.pdfcampus.entity.User;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

import java.util.*;

@Component
public class JwtTokenProvider {
    // JWT 시크릿 키
    @Value("${app.jwtSecret}")
    private String jwtSecret;

    // JWT 유효 시간 (30분)
    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    public String generateToken(User user) {
        // JWT 페이로드에 저장될 정보
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", user.getUid());

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}