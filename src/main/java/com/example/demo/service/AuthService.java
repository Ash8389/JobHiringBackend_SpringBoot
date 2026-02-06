package com.example.demo.service;

import com.example.demo.jwt.utils.JwtUtils;
import com.example.demo.model.RevokedToken;
import com.example.demo.repository.RevokedTokenRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {
    private final RevokedTokenRepository revokedTokenRepository;
    private final JwtUtils jwtUtils;

    AuthService(RevokedTokenRepository revokedTokenRepository, JwtUtils jwtUtils) {
        this.revokedTokenRepository = revokedTokenRepository;
        this.jwtUtils = jwtUtils;
    }

    public String logout(String token){
        String tokenId = jwtUtils.extractTokenId(token);
        Date expiry = jwtUtils.extractExpiry(token);

        RevokedToken revokedToken = new RevokedToken();
        revokedToken.setTokenId(tokenId);
        revokedToken.setExpiry(expiry);

        revokedTokenRepository.save(revokedToken);

        return "Logged out successfully";
    }

}
