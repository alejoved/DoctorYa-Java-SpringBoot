package com.project.doctorya.auth.application.interfaces;

import com.project.doctorya.auth.domain.models.Auth;

public interface IAuthRegisterUseCase {
    public Auth register(Auth auth);
}
