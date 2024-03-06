package com.example.dto.response;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

public class RegisterResponseDto {
    @Email
    private String email;
    @Size(min = 4, max = 20,message = "Username must be between 4 and 20 characters")
    private String username;
    @Size(min = 8, max = 20,message = "Password must be between 8 and 20 characters")
    private String password;
    private String activationCode;
}
