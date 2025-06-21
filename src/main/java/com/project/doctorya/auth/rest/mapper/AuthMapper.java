package com.project.doctorya.auth.rest.mapper;

import com.project.doctorya.auth.domain.model.AuthModel;
import com.project.doctorya.auth.rest.dto.AuthDTO;
import com.project.doctorya.auth.rest.dto.AuthResponseDTO;
import com.project.doctorya.auth.rest.dto.TokenResponseDTO;

public class AuthMapper {
    public static AuthModel toDomain(AuthDTO authDTO){
        AuthModel authModel = new AuthModel();
        authModel.setIdentification(authDTO.getIdentification());
        authModel.setPassword(authDTO.getPassword());
        return authModel;
    }
    public static AuthResponseDTO toDTO(AuthModel authModel){
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
