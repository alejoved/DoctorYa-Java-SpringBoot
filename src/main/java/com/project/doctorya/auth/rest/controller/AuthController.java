package com.project.doctorya.auth.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.doctorya.auth.application.interfaces.IAuthLoginUseCase;
import com.project.doctorya.auth.application.interfaces.IAuthRegisterUseCase;
import com.project.doctorya.auth.domain.models.Auth;
import com.project.doctorya.auth.rest.dto.AuthDto;
import com.project.doctorya.auth.rest.dto.AuthResponseDto;
import com.project.doctorya.auth.rest.dto.TokenResponseDto;
import com.project.doctorya.auth.rest.mapper.AuthMapper;

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
    private IAuthLoginUseCase authLoginUseCase;
    @Autowired
    private IAuthRegisterUseCase authRegisterUseCase;

    @Operation(summary = "Sign in with credentials, identification and password")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Log in successfully"),
        @ApiResponse(responseCode = "404", description = "Identification or password not match"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })    
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody @Valid AuthDto authDTO) {
        Auth authModel = AuthMapper.toDomain(authDTO);
        String token = authLoginUseCase.login(authModel);
        TokenResponseDto tokenResponseDTO = AuthMapper.toTokenResponseDTO(token);
        return ResponseEntity.ok(tokenResponseDTO);
    }
    @Operation(summary = "Sign up with credentials, identification and password")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Register credentials successfully"),
		@ApiResponse(responseCode = "409", description = "User already exists"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })  
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody @Valid AuthDto authDTO) {
        Auth authModel = AuthMapper.toDomain(authDTO);
        Auth response = authRegisterUseCase.register(authModel);
        AuthResponseDto authResponseDTO = AuthMapper.toDTO(response);
        return ResponseEntity.ok(authResponseDTO);
    }
}
