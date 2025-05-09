package com.project.doctorya.physician.dto;

import com.project.doctorya.auth.dto.AuthResponseDTO;

import lombok.Data;

@Data
public class PhysicianResponseDTO {
    private String name;
    private String code;
    private String speciality;
    private AuthResponseDTO auth;
}
