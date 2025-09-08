package com.project.doctorya.patient.infrastructure.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.doctorya.exceptions.EntityNotExistsException;
import com.project.doctorya.patient.domain.models.Patient;
import com.project.doctorya.patient.domain.repositories.IPatientRepository;
import com.project.doctorya.patient.infrastructure.entities.PatientEntity;
import com.project.doctorya.patient.infrastructure.mappers.PatientMapper;
import com.project.doctorya.shared.Constants;

@Repository
@Primary
public class PatientRepository implements IPatientRepository {

    @Autowired
    private IPatientJpaRepository patientRepository;

    @Override
    public List<Patient> get() {
        List<PatientEntity> patients = patientRepository.findAll();
        return patients.stream().map(PatientMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Patient getById(UUID id) {
        Optional<PatientEntity> patient = patientRepository.findById(id);
        if(patient.isEmpty()){
            return null;
        }
        return PatientMapper.toDomain(patient.get());

    }

    @Override
    public Patient getByIdentification(String identification) {
        Optional<PatientEntity> patient = patientRepository.findByAuthEntityIdentification(identification);
        if(patient.isEmpty()){
            return null;
        }
        return PatientMapper.toDomain(patient.get());
    }

    @Override
    public Patient create(Patient patient) {
        PatientEntity patientEntity = PatientMapper.toEntity(patient);
        PatientEntity response = patientRepository.save(patientEntity);
        return PatientMapper.toDomain(response);
    }

    @Override
    public Patient update(Patient patient) {
        PatientEntity patientEntity = PatientMapper.toEntity(patient);
        PatientEntity response = patientRepository.save(patientEntity);
        return PatientMapper.toDomain(response);
    }

    @Override
    public void delete(UUID id) {
        Optional<PatientEntity> patient = patientRepository.findById(id);
        if(patient.isEmpty()){
            throw new EntityNotExistsException(Constants.patientNotFound);
        }
        patientRepository.delete(patient.get());
    }
    
}
