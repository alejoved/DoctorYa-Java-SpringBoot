package com.project.doctorya.services.interf;

import com.project.doctorya.dtos.LoginDTO;
import com.project.doctorya.dtos.LoginResponseDTO;

public interface IUserService {
    public LoginResponseDTO login(LoginDTO loginDTO);
    public LoginResponseDTO register(LoginDTO loginDTO);
}
