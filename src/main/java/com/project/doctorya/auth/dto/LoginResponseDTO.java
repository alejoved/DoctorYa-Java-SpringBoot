package com.project.doctorya.auth.dto;

import com.project.doctorya.common.Role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoginResponseDTO {
    @Schema(description = "JWT Token encrypt", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWJqZWN0Ijp7ImlkZW50aWZpY2F0aW9uIjoiMTA1Mzg")
    private String token;
    @Schema(description = "Role admin, patient, physician", example = "ADMIN")
    private Role role;

    public LoginResponseDTO(String token, Role role){
        this.token = token;
        this.role = role;
    }
}
