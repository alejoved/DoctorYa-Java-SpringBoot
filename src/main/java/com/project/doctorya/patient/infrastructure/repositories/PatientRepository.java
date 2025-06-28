package com.project.doctorya.patient.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.doctorya.exceptions.EntityNotExistsException;
import com.project.doctorya.patient.domain.model.PatientModel;
import com.project.doctorya.patient.domain.repository.IPatientRepository;
import com.project.doctorya.patient.infrastructure.entity.Patient;
import com.project.doctorya.patient.infrastructure.mapper.PatientMapper;
import com.project.doctorya.shared.Constants;

@Repository
@Primary
public class PatientRepository implements IPatientRepository {

    @Autowired
    private IPatientJpaRepository patientRepository;

    @Override
    public List<PatientModel> get() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(PatientMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public PatientModel getById(UUID id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if(patient.isEmpty()){
            return null;
        }
        return PatientMapper.toDomain(patient.get());

    }

    @Override
    public PatientModel getByIdentification(String identification) {
        Optional<Patient> patient = patientRepository.findByAuthIdentification(identification);
        if(patient.isEmpty()){
            return null;
        }
        return PatientMapper.toDomain(patient.get());
    }

    @Override
    public PatientModel create(PatientModel patientModel) {
        Patient patient = PatientMapper.toEntity(patientModel);
        Patient response = patientRepository.save(patient);
        return PatientMapper.toDomain(response);
    }

    @Override
    public PatientModel update(PatientModel patientModel) {
        Patient patient = PatientMapper.toEntity(patientModel);
        Patient response = patientRepository.save(patient);
        return PatientMapper.toDomain(response);
    }

    @Override
    public void delete(UUID id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if(patient.isEmpty()){
            throw new EntityNotExistsException(Constants.patientNotFound);
        }
        patientRepository.delete(patient.get());
    }
    
}
