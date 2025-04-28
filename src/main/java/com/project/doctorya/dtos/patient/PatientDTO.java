package com.project.doctorya.dtos.patient;

import jakarta.validation.constraints.NotNull;

public class PatientDTO {
    @NotNull
    private String name;
    @NotNull
    private String insurance;

    public String getName() {
        return name;
    }
    public String getInsurance() {
        return insurance;
    }
}
