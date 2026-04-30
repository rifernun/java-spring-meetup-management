package com.richard.meetup.management.user.service;

import com.richard.meetup.management.auth.dto.RegisterRequestDTO;
import com.richard.meetup.management.user.dto.UserResponseDto;

import java.util.UUID;

public interface IUserService {
    void createUser(RegisterRequestDTO data);
    void updateUserRole(UUID id);
}
