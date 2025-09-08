package com.project.doctorya.patient.rest.mapper;

import com.project.doctorya.auth.domain.models.Auth;
import com.project.doctorya.patient.domain.models.Patient;
import com.project.doctorya.patient.rest.dto.PatientDTO;
import com.project.doctorya.patient.rest.dto.PatientResponseDTO;

public class PatientRestMapper {
    public static Patient toDomain(PatientDTO patientDTO){
        Patient patient = new Patient();
        patient.setName(patientDTO.getName());
        patient.setInsurance(patientDTO.getInsurance());
        patient.setAuth(new Auth());
        patient.getAuth().setIdentification(patientDTO.getIdentification());
        patient.getAuth().setPassword(patientDTO.getPassword());
        return patient;

    }
    public static PatientResponseDTO toDTO(Patient patient){
        PatientResponseDTO patientResponseDTO = new PatientResponseDTO();
        patientResponseDTO.setId(patient.getId());
        patientResponseDTO.setName(patient.getName());
        patientResponseDTO.setInsurance(patient.getInsurance());
        patientResponseDTO.setIdentification(patient.getAuth().getIdentification());
        return patientResponseDTO;
    }
}