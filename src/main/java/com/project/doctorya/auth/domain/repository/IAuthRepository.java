package com.project.doctorya.auth.domain.repository;

import java.util.List;

import com.project.doctorya.auth.domain.model.AuthModel;

public interface IAuthRepository {
    public List<AuthModel> get();
    public AuthModel getByIdentification(String identification);
    public AuthModel create(AuthModel authModel);
}