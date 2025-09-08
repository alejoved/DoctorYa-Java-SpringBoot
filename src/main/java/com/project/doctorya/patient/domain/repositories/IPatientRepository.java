package com.project.doctorya.patient.domain.repositories;

import java.util.List;
import java.util.UUID;

import com.project.doctorya.patient.domain.models.Patient;

public interface IPatientRepository {
    public List<Patient> get();
    public Patient getById(UUID id);
    public Patient getByIdentification(String identification);
    public Patient create(Patient patient);
    public Patient update(Patient patient);
    public void delete(UUID id);
}
