package com.project.doctorya.patient.domain.repository;

import java.util.List;
import java.util.UUID;

import com.project.doctorya.patient.domain.model.PatientModel;

public interface IPatientRepository {
    public List<PatientModel> get();
    public PatientModel getById(UUID id);
    public PatientModel getByIdentification(String identification);
    public PatientModel create(PatientModel patientModel);
    public PatientModel update(PatientModel patientModel, UUID id);
    public void delete(UUID id);
}
