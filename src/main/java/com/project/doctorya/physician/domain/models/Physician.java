package com.project.doctorya.physician.domain.models;

import java.util.UUID;

import com.project.doctorya.auth.domain.models.Auth;

import lombok.Data;

@Data
public class Physician {
    private UUID id;
    private String name;
    private String code;
    private String speciality;
    private Auth auth;
}
