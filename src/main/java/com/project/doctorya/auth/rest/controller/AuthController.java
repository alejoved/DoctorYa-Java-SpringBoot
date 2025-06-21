package com.project.doctorya.auth.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.doctorya.auth.application.port.IAuthLoginUseCase;
import com.project.doctorya.auth.application.port.IAuthRegisterUseCase;
import com.project.doctorya.auth.domain.model.AuthModel;
import com.project.doctorya.auth.rest.dto.AuthDTO;
import com.project.doctorya.auth.rest.dto.AuthResponseDTO;
import com.project.doctorya.auth.rest.dto.TokenResponseDTO;
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
    public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid AuthDTO authDTO) {
        AuthModel authModel = AuthMapper.toDomain(authDTO);
        String token = authLoginUseCase.login(authModel);
        TokenResponseDTO tokenResponseDTO = AuthMapper.toTokenResponseDTO(token);
        return ResponseEntity.ok(tokenResponseDTO);
    }
    @Operation(summary = "Sign up with credentials, identification and password")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Register credentials successfully"),
		@ApiResponse(responseCode = "409", description = "User already exists"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })  
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody @Valid AuthDTO authDTO) {
        AuthModel authModel = AuthMapper.toDomain(authDTO);
        AuthModel response = authRegisterUseCase.register(authModel);
        AuthResponseDTO authResponseDTO = AuthMapper.toDTO(response);
        return ResponseEntity.ok(authResponseDTO);
    }
}
