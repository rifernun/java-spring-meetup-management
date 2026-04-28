package com.richard.meetup.management.user.dto;

import com.richard.meetup.management.user.entity.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequestDto(
        @NotBlank String username,
    @NotBlank @Email String email,
    @NotBlank String password,
    @NotNull UserRole role
) {}
