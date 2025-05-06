package com.project.doctorya.auth.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LoginDTO {
    @NotNull
    @Size(min = 4)
    private String identification;
    @NotNull
    private String password;

    public String getIdentification() {
        return identification;
    }
    public String getPassword() {
        return password;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
