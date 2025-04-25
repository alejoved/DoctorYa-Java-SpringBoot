package com.project.doctorya.dtos;

import java.util.Date;

public class LoginResponseDTO {
    private String identification;
    private String token;
    private Date expiration;

    public LoginResponseDTO(String identification, String token, Date expiration){
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

    public Date getExpiration() {
        return expiration;
    }
    
}
