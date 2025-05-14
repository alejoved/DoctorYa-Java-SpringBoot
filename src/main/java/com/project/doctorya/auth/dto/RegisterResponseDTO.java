package com.project.doctorya.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RegisterResponseDTO {
    @Schema(description = "Primary identification for the sign up", example = "1053748590")
    private String identification;
}
