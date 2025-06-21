package com.project.doctorya.patient.infrastructure.mapper;

import com.project.doctorya.auth.domain.model.AuthModel;
import com.project.doctorya.auth.infrastructure.entity.Auth;
import com.project.doctorya.patient.domain.model.PatientModel;
import com.project.doctorya.patient.infrastructure.entity.Patient;

public class PatientMapper {
    public static PatientModel toDomain(Patient patient){
        PatientModel patientModel = new PatientModel();
        patientModel.setName(patient.getName());
        patientModel.setInsurance(patient.getInsurance());
        patientModel.setAuthModel(new AuthModel());
        patientModel.getAuthModel().setIdentification(patient.getAuth().getIdentification());
        patientModel.getAuthModel().setRole(patient.getAuth().getRole());
        return patientModel;

    }
    public static Patient toEntity(PatientModel patientModel){
        Patient patient = new Patient();
        patient.setName(patientModel.getName());
        patient.setInsurance(patientModel.getInsurance());
        patient.setAuth(new Auth());
        patient.getAuth().setId(patientModel.getAuthModel().getId());
        return patient;
    }
}
