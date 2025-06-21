package com.project.doctorya.patient.application.port;

import java.util.UUID;

import com.project.doctorya.patient.domain.model.PatientModel;

public interface IPatientUpdateUseCase {
    public PatientModel execute(PatientModel patientModel, UUID id);
}
