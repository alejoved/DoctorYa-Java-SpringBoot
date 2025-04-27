package com.project.doctorya.dtos;

public class RegisterResponseDTO {
    private String identification;
    private String token;
    private String expiration;

    public RegisterResponseDTO(String identification, String token, String expiration){
        this.identification = identification;
        this.token = token;
        this.expiration = expiration;
    }
    public String getIdentification() {
        return identification;
    }
    public String getToken() {
        return token;
    }
    public String getExpiration() {
        return expiration;
    }
}
