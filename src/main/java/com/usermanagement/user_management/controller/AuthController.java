package com.usermanagement.user_management.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
