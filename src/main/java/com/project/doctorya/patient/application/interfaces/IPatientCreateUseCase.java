package com.project.doctorya.patient.application.interfaces;

import com.project.doctorya.patient.domain.models.Patient;

public interface IPatientCreateUseCase {
    public Patient execute(Patient patient);
}
