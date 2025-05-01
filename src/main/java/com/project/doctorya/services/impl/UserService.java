package com.project.doctorya.services.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.project.doctorya.auth.JwtUtils;
import com.project.doctorya.dtos.user.LoginDTO;
import com.project.doctorya.dtos.user.LoginResponseDTO;
import com.project.doctorya.dtos.user.RegisterDTO;
import com.project.doctorya.dtos.user.RegisterResponseDTO;
import com.project.doctorya.exceptions.ModelExistsException;
import com.project.doctorya.exceptions.ModelNotExistsException;
import com.project.doctorya.models.User;
import com.project.doctorya.repositories.UserRepository;
import com.project.doctorya.services.interf.IUserService;
import com.project.doctorya.utils.Constants;
import com.project.doctorya.utils.Rol;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    public LoginResponseDTO login(LoginDTO loginDTO){
        Optional<User> user = userRepository.findByIdentification(loginDTO.getIdentification());
        if(user.isEmpty()){
            throw new ModelNotExistsException(Constants.userNotFound);
        }
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.get().getPassword())) {
            throw new BadCredentialsException(Constants.credentialsNotValid);
        }
        String token = JwtUtils.generateToken(user.get().getIdentification(), user.get().getRol().name());
        return new LoginResponseDTO(token, user.get().getRol());
    }

    public RegisterResponseDTO register(RegisterDTO registerDTO){
        try {
            Optional<User> userFound = userRepository.findByIdentification(registerDTO.getIdentification());
            if(userFound.isPresent()){
                throw new ModelExistsException(Constants.userExists);
            }
            User user = modelMapper.map(registerDTO, User.class);
            String password = passwordEncoder.encode(registerDTO.getPassword());
            user.setPassword(password);
            user.setRol(Rol.ADMIN);
            User response = userRepository.save(user);
            RegisterResponseDTO registerResponseDTO = modelMapper.map(response, RegisterResponseDTO.class);
            return registerResponseDTO;
        } catch(DataIntegrityViolationException ex){
            throw new ModelExistsException(Constants.userExists);
        }
    }   
}
