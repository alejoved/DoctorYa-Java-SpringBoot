package com.project.doctorya.patient.infrastructure.mapper;

import com.project.doctorya.auth.domain.model.AuthModel;
import com.project.doctorya.auth.infrastructure.entity.Auth;
import com.project.doctorya.patient.domain.model.PatientModel;
import com.project.doctorya.patient.infrastructure.entity.Patient;

public class PatientMapper {
    public static PatientModel toDomain(Patient patient){
        PatientModel patientModel = new PatientModel();
        patientModel.setId(patient.getId());
        patientModel.setName(patient.getName());
        patientModel.setInsurance(patient.getInsurance());
        patientModel.setAuthModel(new AuthModel());
        patientModel.getAuthModel().setIdentification(patient.getAuth().getIdentification());
        return patientModel;

    }
    public static Patient toEntity(PatientModel patientModel){
        Patient patient = new Patient();
        patient.setName(patientModel.getName());
        patient.setInsurance(patientModel.getInsurance());
        patient.setAuth(new Auth());
        patient.getAuth().setIdentification(patientModel.getAuthModel().getIdentification());
        patient.getAuth().setPassword(patientModel.getAuthModel().getPassword());
        patient.getAuth().setRole(patientModel.getAuthModel().getRole());
        return patient;
    }
}
