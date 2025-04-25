package com.project.doctorya.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.doctorya.dtos.LoginDTO;
import com.project.doctorya.dtos.LoginResponseDTO;
import com.project.doctorya.services.interf.IUserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IUserService service;
        
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        LoginResponseDTO loginResponseDTO = service.login(loginDTO);
        return ResponseEntity.ok(loginResponseDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponseDTO> register(@RequestBody LoginDTO loginDTO) {
        LoginResponseDTO loginResponseDTO = service.register(loginDTO);
        return ResponseEntity.ok(loginResponseDTO);
    }
}
