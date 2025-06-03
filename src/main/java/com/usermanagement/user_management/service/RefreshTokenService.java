package com.usermanagement.user_management.service;

import java.time.Duration;

import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.RedisTemplate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    
    private final RedisTemplate<String, String> redisTemplate;

    public void storeToken(String email, String refreshToken){
        redisTemplate.opsForValue().set("refresh:" + email, refreshToken, Duration.ofDays(7));
    }

    public String getToken(String email) {
        return redisTemplate.opsForValue().get("refresh:" + email);
    }

    public void deleteToken(String email){
        redisTemplate.delete("refresh:" + email);
    }
}
