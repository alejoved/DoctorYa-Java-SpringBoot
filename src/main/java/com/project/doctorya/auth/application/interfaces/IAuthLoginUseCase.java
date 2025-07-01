package com.project.doctorya.auth.application.interfaces;

import com.project.doctorya.auth.domain.models.AuthModel;

public interface IAuthLoginUseCase {
    public String login(AuthModel authModel);
}
