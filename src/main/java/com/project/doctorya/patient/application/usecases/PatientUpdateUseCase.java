package com.project.doctorya.patient.application.usecases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.doctorya.exceptions.EntityNotExistsException;
import com.project.doctorya.patient.application.interfaces.IPatientUpdateUseCase;
import com.project.doctorya.patient.domain.models.PatientModel;
import com.project.doctorya.patient.domain.repositories.IPatientRepository;
import com.project.doctorya.shared.Constants;

@Service
public class PatientUpdateUseCase implements IPatientUpdateUseCase {

    @Autowired
    private IPatientRepository patientRepository;

    @Override
    public PatientModel execute(PatientModel patientModel, UUID id) {
        PatientModel patientExists = patientRepository.getById(id);
        if(patientExists == null){
            throw new EntityNotExistsException(Constants.patientNotFound);
        }
        patientModel.setId(id);
        patientModel.setAuthModel(patientExists.getAuthModel());
        return patientRepository.update(patientModel);
    }
    
}
