package com.project.doctorya.auth.application.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.doctorya.auth.application.port.IAuthRegisterUseCase;
import com.project.doctorya.auth.domain.model.AuthModel;
import com.project.doctorya.auth.domain.repository.IAuthRepository;
import com.project.doctorya.exceptions.EntityExistsException;
import com.project.doctorya.shared.Constants;
import com.project.doctorya.shared.Role;

@Service
public class AuthRegisterUseCase implements IAuthRegisterUseCase {

    @Autowired
    private IAuthRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthModel register(AuthModel authModel) {
        AuthModel authFound = authRepository.getByIdentification(authModel.getIdentification());
        if(authFound != null){
            throw new EntityExistsException(Constants.authExists);
        }
        String password = passwordEncoder.encode(authModel.getPassword());
        authModel.setPassword(password);
        authModel.setRole(Role.ADMIN);
        return authRepository.create(authModel);
    }
    
}
