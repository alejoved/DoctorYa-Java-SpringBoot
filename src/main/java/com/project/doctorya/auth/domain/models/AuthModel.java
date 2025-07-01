package com.project.doctorya.auth.domain.models;

import java.util.UUID;
import com.project.doctorya.shared.Role;
import lombok.Data;

@Data
public class AuthModel {
    private UUID id;
    private String identification;
    private String password;
    private Role role;
}
