package com.project.doctorya.auth.application.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.doctorya.auth.application.interfaces.IAuthLoginUseCase;
import com.project.doctorya.auth.domain.models.Auth;
import com.project.doctorya.auth.domain.repositories.IAuthRepository;
import com.project.doctorya.shared.Constants;
import com.project.doctorya.shared.config.JwtUtils;
import com.project.doctorya.exceptions.EntityNotExistsException;

@Service
public class AuthLoginUseCase implements IAuthLoginUseCase {

    @Autowired
    private IAuthRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String login(Auth auth) {
        Auth authExists = authRepository.getByIdentification(auth.getIdentification());
        if(authExists == null){
            throw new EntityNotExistsException(Constants.authNotFound);
        }
        if (!passwordEncoder.matches(auth.getPassword(), authExists.getPassword())) {
            throw new BadCredentialsException(Constants.credentialsNotValid);
        }
        String token = JwtUtils.generateToken(authExists.getIdentification(), authExists.getRole().name());
        return token;
    }
    
}
