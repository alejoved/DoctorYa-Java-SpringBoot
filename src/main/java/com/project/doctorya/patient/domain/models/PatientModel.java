package com.project.doctorya.patient.domain.models;
import java.util.UUID;

import com.project.doctorya.auth.domain.models.AuthModel;

import lombok.Data;

@Data
public class PatientModel {
    private UUID id;
    private String name;
    private String insurance;
    private AuthModel authModel;
}
