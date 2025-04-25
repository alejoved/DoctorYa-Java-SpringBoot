package com.project.doctorya.services.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.doctorya.auth.JwtUser;
import com.project.doctorya.auth.JwtUtils;
import com.project.doctorya.dtos.LoginDTO;
import com.project.doctorya.dtos.LoginResponseDTO;
import com.project.doctorya.exceptions.NotFoundException;
import com.project.doctorya.models.User;
import com.project.doctorya.repositories.UserRepository;
import com.project.doctorya.services.interf.IUserService;

@Service
public class UserService implements UserDetailsService, IUserService {
    @Autowired
    private UserRepository userRepository; // Asegúrate de tener este repositorio creado

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String identification) throws UsernameNotFoundException {
        User user = userRepository.findByIdentification(identification)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con identificación: " + identification));
        return new JwtUser(user);
    }

    public LoginResponseDTO login(LoginDTO loginDTO){
        Optional<User> user = userRepository.findByIdentification(loginDTO.getIdentification());
        if(user.isEmpty()){
            throw new NotFoundException("USER NOT FOUND");
        }
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.get().getPassword())) {
            throw new BadCredentialsException("CREDENTIALS NOT VALIDS");
        }
        String token = JwtUtils.generateToken(loginDTO.getIdentification());
        return new LoginResponseDTO(user.get().getIdentification(), token, null);
    }

    public LoginResponseDTO register(LoginDTO loginDTO){
        String password = passwordEncoder.encode(loginDTO.getPassword());
        loginDTO.setPassword(password);
        User user = modelMapper.map(loginDTO, User.class);
        User response = userRepository.save(user);
        String token = JwtUtils.generateToken(loginDTO.getIdentification());
        return new LoginResponseDTO(response.getIdentification(), token, null);
    }
}
