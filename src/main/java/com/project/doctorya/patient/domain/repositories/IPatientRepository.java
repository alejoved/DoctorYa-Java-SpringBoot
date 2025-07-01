package com.project.doctorya.patient.domain.repositories;

import java.util.List;
import java.util.UUID;

import com.project.doctorya.patient.domain.models.PatientModel;

public interface IPatientRepository {
    public List<PatientModel> get();
    public PatientModel getById(UUID id);
    public PatientModel getByIdentification(String identification);
    public PatientModel create(PatientModel patientModel);
    public PatientModel update(PatientModel patientModel);
    public void delete(UUID id);
}
