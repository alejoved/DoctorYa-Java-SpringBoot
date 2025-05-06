package com.project.doctorya.auth.dto;

import com.project.doctorya.common.Role;

public class LoginResponseDTO {
    private String token;
    private Role rol;

    public LoginResponseDTO(String token, Role rol){
        this.token = token;
        this.rol = rol;
    }
    public String getToken() {
        return token;
    }
    public Role getRol() {
        return rol;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public void setRol(Role rol) {
        this.rol = rol;
    }    
}
