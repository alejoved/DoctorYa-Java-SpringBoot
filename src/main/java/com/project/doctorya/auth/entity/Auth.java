package com.project.doctorya.auth.entity;

import java.util.UUID;

import com.project.doctorya.common.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "auth")
public class Auth {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(unique = true, nullable = false)
    private String identification;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role rol;
}

