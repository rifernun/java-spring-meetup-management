package com.richard.meetup.management.user.mapper;

import com.richard.meetup.management.user.dto.UserRequestDto;
import com.richard.meetup.management.user.dto.UserResponseDto;
import com.richard.meetup.management.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequestDto dto) {
        if (dto == null) return null;
        User user = new User();
        user.setEmail(dto.email());
        user.setUsername(dto.username());
        user.setPassword(dto.password());
        user.setRole(dto.role());
        return user;
    }

    public UserResponseDto toResponseDto(User user) {
        if (user == null) return null;
        return new UserResponseDto(
            user.getId(),
            user.getEmail(),
            user.getRole()
        );
    }
}
