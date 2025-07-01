package com.project.doctorya.auth.infrastructure.mappers;

import com.project.doctorya.auth.domain.models.AuthModel;
import com.project.doctorya.auth.infrastructure.entities.Auth;

public class AuthMapper {
    public static AuthModel toDomain(Auth auth){
        AuthModel authModel = new AuthModel();
        authModel.setIdentification(auth.getIdentification());
        authModel.setPassword(auth.getPassword());
        authModel.setRole(auth.getRole());
        return authModel;

    }
    public static Auth toEntity(AuthModel authModel){
        Auth auth = new Auth();
        auth.setIdentification(authModel.getIdentification());
        auth.setPassword(authModel.getPassword());
        auth.setRole(authModel.getRole());
        return auth;
    }
}
