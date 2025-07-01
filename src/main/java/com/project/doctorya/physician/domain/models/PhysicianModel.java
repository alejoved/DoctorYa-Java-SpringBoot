package com.project.doctorya.physician.domain.models;

import java.util.UUID;

import com.project.doctorya.auth.domain.models.AuthModel;

import lombok.Data;

@Data
public class PhysicianModel {
    private UUID id;
    private String name;
    private String code;
    private String speciality;
    private AuthModel authModel;
}
