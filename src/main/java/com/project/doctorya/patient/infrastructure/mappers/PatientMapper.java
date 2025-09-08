package com.project.doctorya.patient.infrastructure.mappers;

import com.project.doctorya.auth.domain.models.Auth;
import com.project.doctorya.auth.infrastructure.entities.AuthEntity;
import com.project.doctorya.patient.domain.models.Patient;
import com.project.doctorya.patient.infrastructure.entities.PatientEntity;

public class PatientMapper {
    public static Patient toDomain(PatientEntity patientEntity){
        Patient patient = new Patient();
        patient.setId(patientEntity.getId());
        patient.setName(patientEntity.getName());
        patient.setInsurance(patientEntity.getInsurance());
        patient.setAuth(new Auth());
        patient.getAuth().setId(patientEntity.getAuthEntity().getId());
        patient.getAuth().setIdentification(patientEntity.getAuthEntity().getIdentification());
        patient.getAuth().setPassword(patientEntity.getAuthEntity().getPassword());
        patient.getAuth().setRole(patientEntity.getAuthEntity().getRole());
        return patient;

    }
    public static PatientEntity toEntity(Patient patient){
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(patient.getId());
        patientEntity.setName(patient.getName());
        patientEntity.setInsurance(patient.getInsurance());
        patientEntity.setAuthEntity(new AuthEntity());
        patientEntity.getAuthEntity().setId(patient.getAuth().getId());
        patientEntity.getAuthEntity().setIdentification(patient.getAuth().getIdentification());
        patientEntity.getAuthEntity().setPassword(patient.getAuth().getPassword());
        patientEntity.getAuthEntity().setRole(patient.getAuth().getRole());
        return patientEntity;
    }
}
