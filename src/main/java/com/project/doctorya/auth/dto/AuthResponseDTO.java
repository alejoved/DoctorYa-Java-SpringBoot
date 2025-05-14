package com.project.doctorya.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AuthResponseDTO {
    @Schema(description = "Primary identification for the admin, patient, physician", example = "1053864698")
    private String identification;
}
