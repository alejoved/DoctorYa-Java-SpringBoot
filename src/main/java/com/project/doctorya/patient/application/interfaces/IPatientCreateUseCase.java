package com.project.doctorya.patient.application.port;

import com.project.doctorya.patient.domain.model.PatientModel;

public interface IPatientCreateUseCase {
    public PatientModel execute(PatientModel patientModel);
}
