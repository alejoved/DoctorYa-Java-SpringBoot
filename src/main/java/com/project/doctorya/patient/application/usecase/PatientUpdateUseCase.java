package com.project.doctorya.patient.application.usecase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.doctorya.patient.application.port.IPatientUpdateUseCase;
import com.project.doctorya.patient.domain.model.PatientModel;
import com.project.doctorya.patient.domain.repository.IPatientRepository;

@Service
public class PatientUpdateUseCase implements IPatientUpdateUseCase {

    @Autowired
    private IPatientRepository patientRepository;

    @Override
    public PatientModel execute(PatientModel patientModel, UUID id) {
        return patientRepository.update(patientModel, id);
    }
    
}
