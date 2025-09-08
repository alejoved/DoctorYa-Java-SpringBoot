package com.project.doctorya.physician.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PhysicianResponseDto {
    @Schema(description = "Physician full name")
    private String name;
    @Schema(description = "General medical code")
    private String code;
    @Schema(description = "Speciality field of the physician")
    private String speciality;
    @Schema(description = "Main physician identification")
    private String identification;
}
