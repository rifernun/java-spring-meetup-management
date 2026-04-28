package com.richard.meetup.management.auth.controller;

import com.richard.meetup.management.auth.dto.AuthRequestDTO;
import com.richard.meetup.management.auth.dto.LoginResponseDTO;
import com.richard.meetup.management.auth.dto.RegisterRequestDTO;
import com.richard.meetup.management.auth.service.TokenService;
import com.richard.meetup.management.participant.entity.Participant;
import com.richard.meetup.management.participant.repository.ParticipantRepository;
import com.richard.meetup.management.shared.dto.ResponseDto;
import com.richard.meetup.management.user.entity.User;
import com.richard.meetup.management.user.repository.UserRepository;
import com.richard.meetup.management.user.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final IUserService userService;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, ParticipantRepository participantRepository, TokenService tokenService, PasswordEncoder passwordEncoder, IUserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthRequestDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<ResponseDto> register(@RequestBody @Valid RegisterRequestDTO data) {
        userService.createUser(data);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "User registered successfully"));
    }
}
