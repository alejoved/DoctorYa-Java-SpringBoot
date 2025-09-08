package com.project.doctorya.auth.rest.mapper;

import com.project.doctorya.auth.domain.models.Auth;
import com.project.doctorya.auth.rest.dto.AuthDto;
import com.project.doctorya.auth.rest.dto.AuthResponseDto;
import com.project.doctorya.auth.rest.dto.TokenResponseDto;

public class AuthMapper {
    public static Auth toDomain(AuthDto authDTO){
        Auth authModel = new Auth();
        authModel.setIdentification(authDTO.getIdentification());
        authModel.setPassword(authDTO.getPassword());
        return authModel;
    }
    public static AuthResponseDto toDTO(Auth authModel){
        AuthResponseDto authResponseDTO = new AuthResponseDto();
        authResponseDTO.setIdentification(authModel.getIdentification());
        return authResponseDTO;
    }
    public static TokenResponseDto toTokenResponseDTO(String token){
        TokenResponseDto tokenResponseDTO = new TokenResponseDto();
        tokenResponseDTO.setToken(token);
        return tokenResponseDTO;
    }
}
