package com.project.doctorya.physician.domain.model;

import java.util.UUID;
import com.project.doctorya.auth.domain.model.AuthModel;

import lombok.Data;

@Data
public class PhysicianModel {
    private UUID id;
    private String name;
    private String code;
    private String speciality;
    private AuthModel authModel;
}
