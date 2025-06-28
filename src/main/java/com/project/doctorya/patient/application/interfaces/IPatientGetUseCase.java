package com.project.doctorya.patient.application.port;

import java.util.List;
import java.util.UUID;

import com.project.doctorya.patient.domain.model.PatientModel;

public interface IPatientGetUseCase {
    public List<PatientModel> execute();
    public PatientModel executeById(UUID id);
    public PatientModel executeByIdentification(String identification);
}
