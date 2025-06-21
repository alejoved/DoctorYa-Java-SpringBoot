package com.project.doctorya.patient.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PatientResponseDTO {
    @Schema(description = "Patient full name")
    private String name;
    @Schema(description = "Name of the heatlh insurance")
    private String insurance;
    @Schema(description = "Primary patient identification")
    private String identification;

}
