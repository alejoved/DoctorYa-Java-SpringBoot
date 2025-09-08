package com.project.doctorya.patient.rest.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PatientResponseDto {
    @Schema(description = "Patient ID")
    private UUID id;
    @Schema(description = "Patient full name")
    private String name;
    @Schema(description = "Name of the heatlh insurance")
    private String insurance;
    @Schema(description = "Primary patient identification")
    private String identification;

}
