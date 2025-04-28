package com.project.doctorya.dtos.user;

public class LoginResponseDTO {
    private String token;
    private String expiration;

    public LoginResponseDTO(String token, String expiration){
        this.token = token;
        this.expiration = expiration;
    }

    public String getToken() {
        return token;
    }

    public String getExpiration() {
        return expiration;
    }
    
}
