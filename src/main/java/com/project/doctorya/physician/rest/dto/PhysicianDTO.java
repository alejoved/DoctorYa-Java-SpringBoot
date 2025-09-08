package com.project.doctorya.physician.rest.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PhysicianDto {
    @Schema(description = "Primary identification for the physician")
    @NotNull
    private String identification;
    @Schema(description = "Password for the log in")
    @NotNull
    private String password;
    @Schema(description = "Physician full name")
    @NotNull
    private String name;
    @Schema(description = "General medical code")
    @NotNull
    private String code;
    @Schema(description = "Speciality field of the physician")
    @NotNull
    private String speciality;
}
