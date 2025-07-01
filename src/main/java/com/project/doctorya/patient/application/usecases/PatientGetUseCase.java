package com.project.doctorya.patient.application.usecases;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.doctorya.exceptions.EntityNotExistsException;
import com.project.doctorya.patient.application.interfaces.IPatientGetUseCase;
import com.project.doctorya.patient.domain.models.PatientModel;
import com.project.doctorya.patient.domain.repositories.IPatientRepository;
import com.project.doctorya.shared.Constants;

@Service
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
