package com.project.doctorya.patient.application.interfaces;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.project.doctorya.patient.domain.models.Patient;

@Service
public interface IPatientUpdateUseCase {
    public Patient execute(Patient patientModel, UUID id);
}
