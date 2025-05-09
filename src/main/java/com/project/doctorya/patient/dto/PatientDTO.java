package com.project.doctorya.patient.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PatientDTO {
    @NotNull
    private String identification;
    @NotNull
    private String password;
    @NotNull
    private String name;
    @NotNull
    private String insurance;
}
