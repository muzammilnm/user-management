package com.usermanagement.user_management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RegisterRequestDto {
    private String username;
    private String email;
    private String password;
}
