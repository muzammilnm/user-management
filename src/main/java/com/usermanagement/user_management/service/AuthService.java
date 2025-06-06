package com.usermanagement.user_management.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.usermanagement.user_management.dto.AuthResponseDto;
import com.usermanagement.user_management.dto.LoginRequestDto;
import com.usermanagement.user_management.dto.LoginResponseDto;
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
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    
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

    public LoginResponseDto login(LoginRequestDto request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        var aaccessToken = jwtService.generateToken(request.getEmail());
        var refreshToken = jwtService.generateToken(request.getEmail());

        refreshTokenService.storeToken(user.getEmail(), refreshToken);

        return LoginResponseDto.builder()
            .accessToken(aaccessToken)
            .refreshToken(refreshToken)
            .build();
    }

    public AuthResponseDto refresh(String email, String refreshToken){
        var storedToken = refreshTokenService.getToken(email);

        if (!refreshToken.equals(storedToken) || !jwtService.isTokenValid(refreshToken)) {
            throw new RuntimeException("Invalid refresh token");
        }

        var newAccessToken = jwtService.generateToken(email);
        return AuthResponseDto.builder()
            .accessToken(newAccessToken)
            .refreshToken(refreshToken)
            .build();
    }
}
