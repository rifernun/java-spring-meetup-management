package com.richard.meetup.management.user.mapper;

import com.richard.meetup.management.user.dto.UserRequestDto;
import com.richard.meetup.management.user.dto.UserResponseDto;
import com.richard.meetup.management.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto toResponseDto(User user) {
        if (user == null) return null;
        return new UserResponseDto(
            user.getId(),
            user.getEmail(),
            user.getRole()
        );
    }
}
