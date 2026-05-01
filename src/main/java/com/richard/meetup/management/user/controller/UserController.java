package com.richard.meetup.management.user.controller;

import com.richard.meetup.management.shared.dto.ResponseDto;
import com.richard.meetup.management.user.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Users", description = "Endpoints for managing users (Admin only)")
public class UserController {

    private IUserService iUserService;

    @PatchMapping("/{id}/role")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Update user role",
            description = "Change the role of a user to ADMIN. Requires ADMIN privilege."
    )
    @ApiResponse(responseCode = "200", description = "User role updated successfully")
    public ResponseEntity<ResponseDto> updateUserRole(@PathVariable UUID id) {
        iUserService.updateUserRole(id);
        return ResponseEntity
                .ok(new ResponseDto("200", "User role updated successfully"));
    }
}
