package com.project.doctorya.auth.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterDTO {
    @NotNull
    @Size(min = 4)
    private String identification;
    @NotNull
    private String password;
}
