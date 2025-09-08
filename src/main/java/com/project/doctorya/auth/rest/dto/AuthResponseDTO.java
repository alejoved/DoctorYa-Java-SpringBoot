package com.project.doctorya.auth.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AuthResponseDto {
    @Schema(description = "Primary identification for the admin, patient, physician")
    private String identification;
}
