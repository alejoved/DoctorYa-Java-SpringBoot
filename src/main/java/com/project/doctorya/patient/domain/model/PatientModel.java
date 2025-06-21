package com.project.doctorya.patient.domain.model;
import java.util.UUID;
import com.project.doctorya.auth.domain.model.AuthModel;
import lombok.Data;

@Data
public class PatientModel {
    private UUID id;
    private String name;
    private String insurance;
    private AuthModel authModel;
}
