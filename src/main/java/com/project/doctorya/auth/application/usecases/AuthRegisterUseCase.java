package com.project.doctorya.auth.application.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.doctorya.auth.application.interfaces.IAuthRegisterUseCase;
import com.project.doctorya.auth.domain.models.Auth;
import com.project.doctorya.auth.domain.repositories.IAuthRepository;
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
    public Auth register(Auth authModel) {
        Auth authFound = authRepository.getByIdentification(authModel.getIdentification());
        if(authFound != null){
            throw new EntityExistsException(Constants.authExists);
        }
        String password = passwordEncoder.encode(authModel.getPassword());
        authModel.setPassword(password);
        authModel.setRole(Role.ADMIN);
        return authRepository.create(authModel);
    }
    
}
