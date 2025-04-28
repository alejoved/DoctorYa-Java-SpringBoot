package com.project.doctorya.dtos.user;

import com.project.doctorya.utils.Rol;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegisterDTO {
    @NotNull
    @Size(min = 4)
    private String identification;
    @NotNull
    private String password;
    @NotNull
    private Rol rol;

    public String getIdentification() {
        return identification;
    }
    public String getPassword() {
        return password;
    }
    public void setIdentification(String identification) {
        this.identification = identification;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Rol getRol() {
        return rol;
    }
    public void setRol(Rol rol) {
        this.rol = rol;
    }

}
