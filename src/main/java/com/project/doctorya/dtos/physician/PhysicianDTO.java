package com.project.doctorya.dtos.physician;
import jakarta.validation.constraints.NotNull;

public class PhysicianDTO {
    @NotNull
    private String identification;
    @NotNull
    private String password;
    @NotNull
    private String name;
    @NotNull
    private String code;
    @NotNull
    private String speciality;

    
    public String getIdentification() {
        return identification;
    }
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
    public String getCode() {
        return code;
    }
    public String getSpeciality() {
        return speciality;
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
    public void setCode(String code) {
        this.code = code;
    }
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

}
