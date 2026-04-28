package com.richard.meetup.management.user.service;

import com.richard.meetup.management.auth.dto.RegisterRequestDTO;

import java.util.UUID;

public interface IUserService {
    void createUser(RegisterRequestDTO data);
    void fetchUserByUsername(String username);
    void updateUser(UUID id);
    void updateUserRole(UUID id);
}
