package com.project.doctorya.patient.rest.mapper;

import com.project.doctorya.auth.domain.models.AuthModel;
import com.project.doctorya.patient.domain.models.PatientModel;
import com.project.doctorya.patient.rest.dto.PatientDTO;
import com.project.doctorya.patient.rest.dto.PatientResponseDTO;

public class PatientRestMapper {
    public static PatientModel toDomain(PatientDTO patientDTO){
        PatientModel patientModel = new PatientModel();
        patientModel.setName(patientDTO.getName());
        patientModel.setInsurance(patientDTO.getInsurance());
        patientModel.setAuthModel(new AuthModel());
        patientModel.getAuthModel().setIdentification(patientDTO.getIdentification());
        patientModel.getAuthModel().setPassword(patientDTO.getPassword());
        return patientModel;

    }
    public static PatientResponseDTO toDTO(PatientModel patientModel){
        PatientResponseDTO patientResponseDTO = new PatientResponseDTO();
        patientResponseDTO.setName(patientModel.getName());
        patientResponseDTO.setInsurance(patientModel.getInsurance());
        patientResponseDTO.setIdentification(patientModel.getAuthModel().getIdentification());
        return patientResponseDTO;
    }
}