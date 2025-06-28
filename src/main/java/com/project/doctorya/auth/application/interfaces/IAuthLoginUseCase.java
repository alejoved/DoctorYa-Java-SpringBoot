package com.project.doctorya.auth.application.port;

import com.project.doctorya.auth.domain.model.AuthModel;

public interface IAuthLoginUseCase {
    public String login(AuthModel authModel);
}
