package com.project.doctorya.auth.application.interfaces;

import com.project.doctorya.auth.domain.models.Auth;

public interface IAuthLoginUseCase {
    public String login(Auth auth);
}
