package com.richard.meetup.management.user.dto;

import com.richard.meetup.management.user.entity.enums.UserRole;
import java.util.UUID;

public record UserResponseDto(
    UUID id,
    String email,
    UserRole role
) {}
