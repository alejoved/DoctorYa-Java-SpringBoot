package com.project.doctorya.patient.dto;

import com.project.doctorya.auth.dto.AuthResponseDTO;

import lombok.Data;

@Data
public class PatientResponseDTO {
    private String name;
    private String insurance;
    private AuthResponseDTO auth;

}
