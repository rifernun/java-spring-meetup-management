package com.richard.meetup.management.user.controller;

import com.richard.meetup.management.shared.dto.ResponseDto;
import com.richard.meetup.management.user.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/admin/users", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class UserController {

    private IUserService iUserService;

    @PatchMapping("/update/role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDto> updateUserRole(@PathVariable UUID id) {
        iUserService.updateUserRole(id);
        return ResponseEntity
                .ok(new ResponseDto("200", "User role updated successfully"));
    }
}
