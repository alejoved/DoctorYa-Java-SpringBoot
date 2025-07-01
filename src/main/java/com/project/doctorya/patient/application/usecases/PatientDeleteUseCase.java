package com.project.doctorya.patient.application.usecases;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.doctorya.patient.application.interfaces.IPatientDeleteUseCase;
import com.project.doctorya.patient.domain.repositories.IPatientRepository;

@Service
public class PatientDeleteUseCase implements IPatientDeleteUseCase {

    @Autowired
    private IPatientRepository patientRepository;

    @Override
    public void execute(UUID id) {
        patientRepository.delete(id);
    }
    
}
