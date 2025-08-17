package com.springboot_auth.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProfileRequest {

    @NotBlank(message = "Name should be not empty")
    private String name;

    @Email(message = "Enter valid email address")
    @NotNull(message = "Email should be not empty")
    private String email;

    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
}
