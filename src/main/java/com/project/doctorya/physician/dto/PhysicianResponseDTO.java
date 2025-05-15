package com.project.doctorya.physician.dto;

import java.util.UUID;

import com.project.doctorya.auth.dto.AuthResponseDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PhysicianResponseDTO {
    @Schema(description = "Appointment ID")
    private UUID  id;
    @Schema(description = "Physician full name")
    private String name;
    @Schema(description = "General medical code")
    private String code;
    @Schema(description = "Speciality field of the physician")
    private String speciality;
    @Schema(description = "Authentication data of the physician")
    private AuthResponseDTO auth;
}
