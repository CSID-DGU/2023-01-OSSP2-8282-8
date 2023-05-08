package com.pdfcampus.pdfcampus.util.jwt;

import com.pdfcampus.pdfcampus.entity.User;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

import java.security.SignatureException;
import java.util.*;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private int jwtExpirationInMs = 600000; //10분

    private int refreshTokenExpirationInMs = 604800000; //7일

    public String generateAccessToken(User user) { //access 토큰 생성 메서드

        Map<String, Object> claims = new HashMap<>(); // payload에 저장될 정보들
        claims.put("sub", user.getUserId());

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(user.getUserId())
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String generateRefreshToken(User user) { // refresh 토큰 생성 메서드
        return Jwts.builder()
                .setSubject(user.getUserId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + refreshTokenExpirationInMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException |  ExpiredJwtException | IllegalArgumentException ex) { //SignatureException |
            return false;
        }
    }

}
