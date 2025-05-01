package com.project.doctorya.dtos.patient;

import jakarta.validation.constraints.NotNull;

public class PatientDTO {
    @NotNull
    private String identification;
    @NotNull
    private String password;
    @NotNull
    private String name;
    @NotNull
    private String insurance;

    public String getIdentification() {
        return identification;
    }
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
    public String getInsurance() {
        return insurance;
    }
    public void setIdentification(String identification) {
        this.identification = identification;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }
}
