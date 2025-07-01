package com.project.doctorya.patient.application.interfaces;

import com.project.doctorya.patient.domain.models.PatientModel;

public interface IPatientCreateUseCase {
    public PatientModel execute(PatientModel patientModel);
}
