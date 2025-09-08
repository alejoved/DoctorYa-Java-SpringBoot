package com.project.doctorya.patient.domain.models;
import java.util.UUID;

import com.project.doctorya.auth.domain.models.Auth;

import lombok.Data;

@Data
public class Patient {
    private UUID id;
    private String name;
    private String insurance;
    private Auth auth;
}
