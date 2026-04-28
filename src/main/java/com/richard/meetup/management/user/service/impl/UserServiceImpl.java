package com.richard.meetup.management.user.service.impl;

import com.richard.meetup.management.auth.dto.RegisterRequestDTO;
import com.richard.meetup.management.participant.entity.Participant;
import com.richard.meetup.management.participant.repository.ParticipantRepository;
import com.richard.meetup.management.user.entity.User;
import com.richard.meetup.management.user.entity.enums.UserRole;
import com.richard.meetup.management.user.exception.UserAlreadyExistsException;
import com.richard.meetup.management.user.exception.UserIsAlreadyAnAdminException;
import com.richard.meetup.management.user.exception.UserNotFoundException;
import com.richard.meetup.management.user.repository.UserRepository;
import com.richard.meetup.management.user.service.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final ParticipantRepository participantRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ParticipantRepository participantRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.participantRepository = participantRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createUser(RegisterRequestDTO data) {
        if (this.userRepository.findByEmail(data.email()).isPresent()) {
            throw new UserAlreadyExistsException("User with email " + data.email() + " already exists");
        }

        String encryptedPassword = passwordEncoder.encode(data.password());

        // 1. Create User
        User newUser = new User();
        newUser.setEmail(data.email());
        newUser.setUsername(data.email());
        newUser.setPassword(encryptedPassword);
        newUser.setRole(UserRole.USER);
        newUser.setCreatedAt(Instant.now());
        newUser.setCreatedBy("system");

        User savedUser = this.userRepository.save(newUser);

        Participant newParticipant = new Participant();
        newParticipant.setName(data.name());
        newParticipant.setLinkedin(data.linkedin());
        newParticipant.setUser(savedUser);
        newParticipant.setCreatedAt(Instant.now());
        newParticipant.setCreatedBy("system");

        this.participantRepository.save(newParticipant);
    }

    @Override
    public void fetchUserByUsername(String username) {
        //TODO
    }

    @Override
    public void updateUser(UUID id) {
        //TODO
    }

    @Override
    public void updateUserRole(UUID id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        userRepository.findById(id).ifPresent(user -> {
            if (user.getRole() == UserRole.USER) {
                user.setRole(UserRole.ADMIN);
            } else {
                throw new UserIsAlreadyAnAdminException("User with id " + id + " is already an admin");
            }
            user.setUpdatedAt(Instant.now());
            userRepository.save(user);
        });
    }
}
