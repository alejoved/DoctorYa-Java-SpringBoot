package com.project.doctorya.auth.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.doctorya.auth.dto.LoginDTO;
import com.project.doctorya.auth.dto.LoginResponseDTO;
import com.project.doctorya.auth.dto.RegisterDTO;
import com.project.doctorya.auth.dto.RegisterResponseDTO;
import com.project.doctorya.auth.entity.Auth;
import com.project.doctorya.auth.repository.AuthRepository;
import com.project.doctorya.common.Constants;
import com.project.doctorya.common.Role;
import com.project.doctorya.config.JwtUtils;
import com.project.doctorya.exceptions.ModelExistsException;
import com.project.doctorya.exceptions.ModelNotExistsException;

@Service
public class AuthService implements IAuthService {
    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    public LoginResponseDTO login(LoginDTO loginDTO){
        Optional<Auth> auth = authRepository.findByIdentification(loginDTO.getIdentification());
        if(auth.isEmpty()){
            throw new ModelNotExistsException(Constants.userNotFound);
        }
        if (!passwordEncoder.matches(loginDTO.getPassword(), auth.get().getPassword())) {
            throw new BadCredentialsException(Constants.credentialsNotValid);
        }
        String token = JwtUtils.generateToken(auth.get().getIdentification(), auth.get().getRol().name());
        return new LoginResponseDTO(token, auth.get().getRol());
    }

    public RegisterResponseDTO register(RegisterDTO registerDTO){
        try {
            Optional<Auth> authFound = authRepository.findByIdentification(registerDTO.getIdentification());
            if(authFound.isPresent()){
                throw new ModelExistsException(Constants.userExists);
            }
            Auth auth = modelMapper.map(registerDTO, Auth.class);
            String password = passwordEncoder.encode(registerDTO.getPassword());
            auth.setPassword(password);
            auth.setRol(Role.ADMIN);
            Auth response = authRepository.save(auth);
            RegisterResponseDTO registerResponseDTO = modelMapper.map(response, RegisterResponseDTO.class);
            return registerResponseDTO;
        } catch(DataIntegrityViolationException ex){
            throw new ModelExistsException(Constants.userExists);
        }
    }   
}
