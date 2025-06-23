package com.project.doctorya.patient.application.port;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.project.doctorya.patient.domain.model.PatientModel;

@Service
public interface IPatientUpdateUseCase {
    public PatientModel execute(PatientModel patientModel, UUID id);
}
