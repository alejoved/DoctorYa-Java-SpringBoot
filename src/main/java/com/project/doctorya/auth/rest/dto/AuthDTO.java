package com.project.doctorya.auth.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthDto {
    @Schema(description = "Identification for the login")
    @NotNull
    @Size(min = 4)
    private String identification;
    @Schema(description = "Password for the login minimum 4 characters")
    @NotNull
    private String password;
}
