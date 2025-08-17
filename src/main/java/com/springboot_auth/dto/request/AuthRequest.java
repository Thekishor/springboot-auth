package com.springboot_auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequest {

    @Email(message = "Enter valid email address")
    @NotNull(message = "Email should be not empty")
    private String email;

    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
}
