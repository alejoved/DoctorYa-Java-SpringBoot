package com.project.doctorya.services.interf;

import com.project.doctorya.dtos.LoginDTO;
import com.project.doctorya.dtos.LoginResponseDTO;
import com.project.doctorya.dtos.RegisterDTO;
import com.project.doctorya.dtos.RegisterResponseDTO;

public interface IUserService {
    public RegisterResponseDTO register(RegisterDTO loginDTO);
    public LoginResponseDTO login(LoginDTO loginDTO);
}
