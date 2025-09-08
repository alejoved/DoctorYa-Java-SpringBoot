package com.project.doctorya.auth.rest.mapper;

import com.project.doctorya.auth.domain.models.Auth;
import com.project.doctorya.auth.rest.dto.AuthDTO;
import com.project.doctorya.auth.rest.dto.AuthResponseDTO;
import com.project.doctorya.auth.rest.dto.TokenResponseDTO;

public class AuthMapper {
    public static Auth toDomain(AuthDTO authDTO){
        Auth authModel = new Auth();
        authModel.setIdentification(authDTO.getIdentification());
        authModel.setPassword(authDTO.getPassword());
        return authModel;
    }
    public static AuthResponseDTO toDTO(Auth authModel){
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setIdentification(authModel.getIdentification());
        return authResponseDTO;
    }
    public static TokenResponseDTO toTokenResponseDTO(String token){
        TokenResponseDTO tokenResponseDTO = new TokenResponseDTO();
        tokenResponseDTO.setToken(token);
        return tokenResponseDTO;
    }
}
