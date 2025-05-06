package com.project.doctorya.physician.dto;

public class PhysicianResponseDTO {
    private String name;
    private String code;
    private String speciality;

    public String getName() {
        return name;
    }
    public String getCode() {
        return code;
    }
    public String getSpeciality() {
        return speciality;
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
