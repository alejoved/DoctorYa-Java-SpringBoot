package com.project.doctorya.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterDTO {
    @Schema(description = "Primary identification for the sign up")
    @NotNull
    @Size(min = 4)
    private String identification;
    @Schema(description = "Password for the sign up")
    @NotNull
    private String password;
}
