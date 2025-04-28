package com.project.doctorya.dtos.user;

public class RegisterResponseDTO {
    private String identification;

    public RegisterResponseDTO(String identification){
        this.identification = identification;
    }
    public String getIdentification() {
        return identification;
    }
}
