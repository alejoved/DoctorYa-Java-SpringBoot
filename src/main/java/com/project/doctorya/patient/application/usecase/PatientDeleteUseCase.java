package com.project.doctorya.patient.application.usecase;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.doctorya.patient.application.port.IPatientDeleteUseCase;
import com.project.doctorya.patient.domain.repository.IPatientRepository;

public class PatientDeleteUseCase implements IPatientDeleteUseCase {

    @Autowired
    private IPatientRepository patientRepository;

    @Override
    public void execute(UUID id) {
        patientRepository.delete(id);
    }
    
}
