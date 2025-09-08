package com.project.doctorya.patient.application.usecases;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.doctorya.exceptions.EntityNotExistsException;
import com.project.doctorya.patient.application.interfaces.IPatientGetUseCase;
import com.project.doctorya.patient.domain.models.Patient;
import com.project.doctorya.patient.domain.repositories.IPatientRepository;
import com.project.doctorya.shared.Constants;

@Service
public class PatientGetUseCase implements IPatientGetUseCase {

    @Autowired
    private IPatientRepository patientRepository;

    @Override
    public List<Patient> execute() {
        List<Patient> patients = patientRepository.get();
        return patients;
    }

    @Override
    public Patient executeById(UUID id) {
        Patient patient = patientRepository.getById(id);
        if (patient == null){
            throw new EntityNotExistsException(Constants.patientNotFound);
        }
        return patient;
    }

    @Override
    public Patient executeByIdentification(String identification) {
        Patient patientModel = patientRepository.getByIdentification(identification);
        if (patientModel == null){
            throw new EntityNotExistsException(Constants.patientNotFound);
        }
        return patientModel;
    }
    
}
