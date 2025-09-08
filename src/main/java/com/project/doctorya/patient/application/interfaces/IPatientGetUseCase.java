package com.project.doctorya.patient.application.interfaces;

import java.util.List;
import java.util.UUID;

import com.project.doctorya.patient.domain.models.Patient;

public interface IPatientGetUseCase {
    public List<Patient> execute();
    public Patient executeById(UUID id);
    public Patient executeByIdentification(String identification);
}
