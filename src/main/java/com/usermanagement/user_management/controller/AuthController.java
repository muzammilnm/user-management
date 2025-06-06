package com.usermanagement.user_management.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.usermanagement.user_management.dto.AuthResponseDto;
import com.usermanagement.user_management.dto.LoginRequestDto;
import com.usermanagement.user_management.dto.LoginResponseDto;
import com.usermanagement.user_management.dto.RegisterRequestDto;
import com.usermanagement.user_management.dto.RegisterResponseDto;
import com.usermanagement.user_management.entity.Users;
import com.usermanagement.user_management.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public HttpEntity<RegisterResponseDto> register(@RequestBody RegisterRequestDto request){
        Users user = authService.register(request);
        RegisterResponseDto response = RegisterResponseDto.builder().email(user.getEmail()).build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public HttpEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request){
        LoginResponseDto response = authService.login(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public HttpEntity<AuthResponseDto> refresh(@RequestParam String email, @RequestParam String refreshToken){
        var response = authService.refresh(email, refreshToken);
        return ResponseEntity.ok(response);
    }
}
