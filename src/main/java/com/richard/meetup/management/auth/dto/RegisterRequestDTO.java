package com.richard.meetup.management.auth.dto;

import com.richard.meetup.management.user.entity.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterRequestDTO(
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank String password,
        @NotBlank String linkedin
) {
}
