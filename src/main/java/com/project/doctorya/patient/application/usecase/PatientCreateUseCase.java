package com.project.doctorya.patient.application.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.doctorya.patient.application.port.IPatientCreateUseCase;
import com.project.doctorya.patient.domain.model.PatientModel;
import com.project.doctorya.patient.domain.repository.IPatientRepository;

@Service
public class PatientCreateUseCase implements IPatientCreateUseCase {

    @Autowired
    private IPatientRepository patientRepository;

    @Override
    public PatientModel execute(PatientModel patientModel) {
        return patientRepository.create(patientModel);
    }

    

}
