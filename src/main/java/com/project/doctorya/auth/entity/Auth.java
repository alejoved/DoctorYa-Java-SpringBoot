package com.project.doctorya.auth.entity;

import com.project.doctorya.common.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "auth")
public class Auth {
    @Id
    @Column(unique = true, nullable = false)
    private String identification;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role rol;

    public String getIdentification() {
        return identification;
    }

    public String getPassword() {
        return password;
    }

    public Role getRol() {
        return rol;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRol(Role rol) {
        this.rol = rol;
    }
}

