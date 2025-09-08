package com.project.doctorya.auth.domain.repositories;

import java.util.List;

import com.project.doctorya.auth.domain.models.Auth;

public interface IAuthRepository {
    public List<Auth> get();
    public Auth getByIdentification(String identification);
    public Auth create(Auth authModel);
}