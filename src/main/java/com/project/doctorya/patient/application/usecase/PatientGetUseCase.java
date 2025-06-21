package com.project.doctorya.patient.application.usecase;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.doctorya.exceptions.EntityNotExistsException;
import com.project.doctorya.patient.application.port.IPatientGetUseCase;
import com.project.doctorya.patient.domain.model.PatientModel;
import com.project.doctorya.patient.domain.repository.IPatientRepository;
import com.project.doctorya.shared.Constants;

public class PatientGetUseCase implements IPatientGetUseCase {

    @Autowired
    private IPatientRepository patientRepository;

    @Override
    public List<PatientModel> execute() {
        List<PatientModel> patients = patientRepository.get();
        return patients;
    }

    @Override
    public PatientModel executeById(UUID id) {
        PatientModel patientModel = patientRepository.getById(id);
        if (patientModel == null){
            throw new EntityNotExistsException(Constants.patientNotFound);
        }
        return patientModel;
    }

    @Override
    public PatientModel executeByIdentification(String identification) {
        PatientModel patientModel = patientRepository.getByIdentification(identification);
        if (patientModel == null){
            throw new EntityNotExistsException(Constants.patientNotFound);
        }
        return patientModel;
    }
    
}
