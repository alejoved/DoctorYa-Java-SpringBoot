package com.project.doctorya.auth.application.interfaces;

import com.project.doctorya.auth.domain.models.AuthModel;

public interface IAuthRegisterUseCase {
    public AuthModel register(AuthModel authModel);
}
