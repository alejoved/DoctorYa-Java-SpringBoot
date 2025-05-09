package com.project.doctorya.physician.dto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
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
}
