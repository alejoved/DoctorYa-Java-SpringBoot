package com.project.doctorya.patient.infrastructure.mappers;

import com.project.doctorya.auth.domain.models.AuthModel;
import com.project.doctorya.auth.infrastructure.entities.Auth;
import com.project.doctorya.patient.domain.models.PatientModel;
import com.project.doctorya.patient.infrastructure.entities.Patient;

public class PatientMapper {
    public static PatientModel toDomain(Patient patient){
        PatientModel patientModel = new PatientModel();
        patientModel.setId(patient.getId());
        patientModel.setName(patient.getName());
        patientModel.setInsurance(patient.getInsurance());
        patientModel.setAuthModel(new AuthModel());
        patientModel.getAuthModel().setId(patient.getAuth().getId());
        patientModel.getAuthModel().setIdentification(patient.getAuth().getIdentification());
        patientModel.getAuthModel().setPassword(patient.getAuth().getPassword());
        patientModel.getAuthModel().setRole(patient.getAuth().getRole());
        return patientModel;

    }
    public static Patient toEntity(PatientModel patientModel){
        Patient patient = new Patient();
        patient.setId(patientModel.getId());
        patient.setName(patientModel.getName());
        patient.setInsurance(patientModel.getInsurance());
        patient.setAuth(new Auth());
        patient.getAuth().setId(patientModel.getAuthModel().getId());
        patient.getAuth().setIdentification(patientModel.getAuthModel().getIdentification());
        patient.getAuth().setPassword(patientModel.getAuthModel().getPassword());
        patient.getAuth().setRole(patientModel.getAuthModel().getRole());
        return patient;
    }
}
