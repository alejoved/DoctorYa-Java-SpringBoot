package com.project.doctorya.services.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import com.project.doctorya.dtos.RegisterDTO;
import com.project.doctorya.dtos.RegisterResponseDTO;
import com.project.doctorya.exceptions.UserExistsException;
import com.project.doctorya.exceptions.UserNotExistsException;
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

    public RegisterResponseDTO register(RegisterDTO registerDTO){
        try {
            String password = passwordEncoder.encode(registerDTO.getPassword());
            registerDTO.setPassword(password);
            User user = modelMapper.map(registerDTO, User.class);
            User response = userRepository.save(user);
            String token = JwtUtils.generateToken(registerDTO.getIdentification());
            Date expiration = new Date(System.currentTimeMillis() + JwtUtils.getJwtexpirationms());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT-5")); // Aquí aplicas GMT-5 solo para formatear
            String expirationFormatted = sdf.format(expiration);
            return new RegisterResponseDTO(response.getIdentification(), token, expirationFormatted);
        }catch(DataIntegrityViolationException ex){
            throw new UserExistsException("User already exists");
        }
        
    }

    public LoginResponseDTO login(LoginDTO loginDTO){
        Optional<User> user = userRepository.findByIdentification(loginDTO.getIdentification());
        if(user.isEmpty()){
            throw new UserNotExistsException("User not found");
        }
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.get().getPassword())) {
            throw new BadCredentialsException("Credentials not valids");
        }
        String token = JwtUtils.generateToken(loginDTO.getIdentification());
        Date expiration = new Date(System.currentTimeMillis() + JwtUtils.getJwtexpirationms());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-5")); // Aquí aplicas GMT-5 solo para formatear
        String expirationFormatted = sdf.format(expiration);
        return new LoginResponseDTO(token, expirationFormatted);
    }

    
}
