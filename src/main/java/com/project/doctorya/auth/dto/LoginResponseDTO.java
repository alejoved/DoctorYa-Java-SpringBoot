package com.project.doctorya.auth.dto;

import com.project.doctorya.common.Role;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String token;
    private Role rol;

    public LoginResponseDTO(String token, Role rol){
        this.token = token;
        this.rol = rol;
    }
}
