package com.usermanagement.user_management.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.usermanagement.user_management.dto.RegisterRequestDto;
import com.usermanagement.user_management.entity.Role;
import com.usermanagement.user_management.entity.Users;
import com.usermanagement.user_management.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    
    public Users register(RegisterRequestDto request){
        var user = Users.builder()
            .username(request.getUsername())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.USER)
            .build();

        userRepository.save(user);

        return user;
    }
}
