package com.project.doctorya.auth.infrastructure.mappers;

import com.project.doctorya.auth.domain.models.Auth;
import com.project.doctorya.auth.infrastructure.entities.AuthEntity;

public class AuthMapper {
    public static Auth toDomain(AuthEntity authEntity){
        Auth auth = new Auth();
        auth.setIdentification(authEntity.getIdentification());
        auth.setPassword(authEntity.getPassword());
        auth.setRole(authEntity.getRole());
        return auth;

    }
    public static AuthEntity toEntity(Auth auth){
        AuthEntity authEntity = new AuthEntity();
        authEntity.setIdentification(auth.getIdentification());
        authEntity.setPassword(auth.getPassword());
        authEntity.setRole(auth.getRole());
        return authEntity;
    }
}
