package com.project.doctorya.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.doctorya.auth.dto.LoginDTO;
import com.project.doctorya.auth.dto.LoginResponseDTO;
import com.project.doctorya.auth.dto.RegisterDTO;
import com.project.doctorya.auth.dto.RegisterResponseDTO;
import com.project.doctorya.auth.service.IAuthService;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthService service;
        
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginDTO loginDTO) {
        LoginResponseDTO loginResponseDTO = service.login(loginDTO);
        return ResponseEntity.ok(loginResponseDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody @Valid RegisterDTO registerDTO) {
        RegisterResponseDTO registerResponseDTO = service.register(registerDTO);
        return ResponseEntity.ok(registerResponseDTO);
    }
}
