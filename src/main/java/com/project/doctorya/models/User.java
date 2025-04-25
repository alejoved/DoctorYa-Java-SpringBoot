package com.project.doctorya.models;

import com.project.doctorya.utils.Rol;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
    @Id
    private String identification; // puede ser cedula, correo, etc.
    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol; // Enum: ADMIN, MEDICO, PACIENTE, etc.

    public String getIdentification() {
        return identification;
    }

    public String getPassword() {
        return password;
    }

    public Rol getRol() {
        return rol;
    }
}
