package com.project.doctorya.patient.dto;

import java.util.UUID;

import com.project.doctorya.auth.dto.AuthResponseDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PatientResponseDTO {
    @Schema(description = "Appointment ID")
    private UUID  id;
    @Schema(description = "Patient full name")
    private String name;
    @Schema(description = "Name of the heatlh insurance")
    private String insurance;
    @Schema(description = "Authentication data of the patient")
    private AuthResponseDTO auth;

}
