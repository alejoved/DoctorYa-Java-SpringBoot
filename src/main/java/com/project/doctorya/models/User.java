package com.project.doctorya.models;

import com.project.doctorya.utils.Rol;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(unique = true, nullable = false)
    private String identification;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Rol rol;

    public String getIdentification() {
        return identification;
    }

    public String getPassword() {
        return password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}

