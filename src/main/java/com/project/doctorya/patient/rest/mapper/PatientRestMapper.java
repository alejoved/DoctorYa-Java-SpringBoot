package com.project.doctorya.patient.rest.mapper;

import com.project.doctorya.auth.domain.models.Auth;
import com.project.doctorya.patient.domain.models.Patient;
import com.project.doctorya.patient.rest.dto.PatientDto;
import com.project.doctorya.patient.rest.dto.PatientResponseDto;

public class PatientRestMapper {
    public static Patient toDomain(PatientDto patientDTO){
        Patient patient = new Patient();
        patient.setName(patientDTO.getName());
        patient.setInsurance(patientDTO.getInsurance());
        patient.setAuth(new Auth());
        patient.getAuth().setIdentification(patientDTO.getIdentification());
        patient.getAuth().setPassword(patientDTO.getPassword());
        return patient;

    }
    public static PatientResponseDto toDto(Patient patient){
        PatientResponseDto patientResponseDto = new PatientResponseDto();
        patientResponseDto.setId(patient.getId());
        patientResponseDto.setName(patient.getName());
        patientResponseDto.setInsurance(patient.getInsurance());
        patientResponseDto.setIdentification(patient.getAuth().getIdentification());
        return patientResponseDto;
    }
}