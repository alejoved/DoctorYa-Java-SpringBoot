package com.project.doctorya.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.doctorya.dtos.user.LoginDTO;
import com.project.doctorya.dtos.user.LoginResponseDTO;
import com.project.doctorya.dtos.user.RegisterDTO;
import com.project.doctorya.dtos.user.RegisterResponseDTO;
import com.project.doctorya.services.interf.IUserService;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService service;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody @Valid RegisterDTO loginDTO) {
        RegisterResponseDTO registerResponseDTO = service.register(loginDTO);
        return ResponseEntity.ok(registerResponseDTO);
    }
        
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginDTO loginDTO) {
        LoginResponseDTO loginResponseDTO = service.login(loginDTO);
        return ResponseEntity.ok(loginResponseDTO);
    }

    
}
