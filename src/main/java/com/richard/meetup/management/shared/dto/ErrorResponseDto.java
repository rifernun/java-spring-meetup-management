package com.richard.meetup.management.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.time.LocalDateTime;

@Data @AllArgsConstructor
public class ErrorResponseDto {
    private String apiPath;
    private HttpStatus errorCode;
    private String errorMsg;
    private Instant errorTime;
}
