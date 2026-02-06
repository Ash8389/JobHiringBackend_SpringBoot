package com.example.demo.jwt.utils;

import com.example.demo.dto.UserRequest;
import com.example.demo.repository.RevokedTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtils {
    @Autowired
    private RevokedTokenRepository revokedTokenRepository;

    private final String SECRET_KEY = "c1baa67ba39f499ccc73a2b7ca9810dd";
    public String tokenGenerator(UserRequest user){
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 60*60*1000))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .compact();
    }

    public String extractorUsername(String token){
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String extractTokenId(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getId();
    }

    public Date extractExpiry(String token) {
        Date date = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();

        return date;
    }
    public boolean validateToken(String token,  UserDetails userDetails){

        boolean isExpired = extractExpiry(token).before(new Date(System.currentTimeMillis()));

        return userDetails.getUsername().equals(extractorUsername(token))
                && !isExpired
                && !revokedTokenRepository.existsById(extractTokenId(token));
    }
}
