package com.project.doctorya.auth.service;

import com.project.doctorya.auth.dto.LoginDTO;
import com.project.doctorya.auth.dto.LoginResponseDTO;
import com.project.doctorya.auth.dto.RegisterDTO;
import com.project.doctorya.auth.dto.RegisterResponseDTO;

public interface IAuthService {
    public LoginResponseDTO login(LoginDTO loginDTO);
    public RegisterResponseDTO register(RegisterDTO registerDTO);
}
