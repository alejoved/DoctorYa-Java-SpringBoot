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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Authentication", description = "Authentication related operations")
@Validated
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthService service;

    @Operation(summary = "Sign in with credentials, identification and password")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Log in successfully"),
        @ApiResponse(responseCode = "404", description = "Identification or password not match"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })    
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginDTO loginDTO) {
        System.out.println(loginDTO.getIdentification());
        LoginResponseDTO loginResponseDTO = service.login(loginDTO);
        return ResponseEntity.ok(loginResponseDTO);
    }
    @Operation(summary = "Sign up with credentials, identification and password")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Register credentials successfully"),
		@ApiResponse(responseCode = "409", description = "User already exists"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })  
    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody @Valid RegisterDTO registerDTO) {
        RegisterResponseDTO registerResponseDTO = service.register(registerDTO);
        return ResponseEntity.ok(registerResponseDTO);
    }
}
