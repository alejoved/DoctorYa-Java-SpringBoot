package com.project.doctorya.patient.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PatientDto {
    @Schema(description = "Primary identification for the patient")
    @NotNull
    private String identification;
    @Schema(description = "Password for the login")
    @NotNull
    private String password;
    @Schema(description = "Patient full name")
    @NotNull
    private String name;
    @Schema(description = "Name of the health insurance")
    @NotNull
    private String insurance;
}
