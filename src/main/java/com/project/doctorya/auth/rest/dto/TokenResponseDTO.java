package com.project.doctorya.auth.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TokenResponseDto {
    @Schema(description = "Main token for the sign in")
    private String token;
}
