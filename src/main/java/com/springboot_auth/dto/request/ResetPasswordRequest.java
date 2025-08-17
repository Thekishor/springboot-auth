package com.springboot_auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordRequest {

    @NotBlank(message = "New Password is required")
    private String newPassword;

    @NotBlank(message = "Otp is required")
    private String otp;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;
}
