package com.project.doctorya.auth.dto;

import com.project.doctorya.common.Rol;

public class LoginResponseDTO {
    private String token;
    private Rol rol;

    public LoginResponseDTO(String token, Rol rol){
        this.token = token;
        this.rol = rol;
    }
    public String getToken() {
        return token;
    }
    public Rol getRol() {
        return rol;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public void setRol(Rol rol) {
        this.rol = rol;
    }    
}
