package com.project.doctorya.patient.dto;

public class PatientResponseDTO {
    private String name;
    private String insurance;

    public String getName() {
        return name;
    }
    public String getInsurance() {
        return insurance;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }
}
