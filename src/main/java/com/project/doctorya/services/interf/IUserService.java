package com.project.doctorya.services.interf;

import com.project.doctorya.dtos.user.LoginDTO;
import com.project.doctorya.dtos.user.LoginResponseDTO;
import com.project.doctorya.dtos.user.RegisterDTO;
import com.project.doctorya.dtos.user.RegisterResponseDTO;

public interface IUserService {
    public LoginResponseDTO login(LoginDTO loginDTO);
    public RegisterResponseDTO register(RegisterDTO loginDTO);
}
