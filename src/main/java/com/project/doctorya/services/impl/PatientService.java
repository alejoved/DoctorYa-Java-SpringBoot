package com.project.doctorya.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.doctorya.dtos.patient.PatientDTO;
import com.project.doctorya.dtos.patient.PatientResponseDTO;
import com.project.doctorya.exceptions.ModelNotExistsException;
import com.project.doctorya.models.Patient;
import com.project.doctorya.repositories.PatientRepository;
import com.project.doctorya.services.interf.IPatientService;

@Service
public class PatientService implements IPatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PatientResponseDTO> getAll() {
        List<Patient> patient = patientRepository.findAll();
        List<PatientResponseDTO> patientResponseDTO = modelMapper.map(patient, new TypeToken<List<PatientResponseDTO>>() {}.getType());
        return patientResponseDTO;
    }

    @Override
    public PatientResponseDTO getById(UUID id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if(patient.isEmpty()){
            throw new ModelNotExistsException("Patient not exists");
        }
        PatientResponseDTO patientResponseDTO = modelMapper.map(patient, PatientResponseDTO.class);
        return patientResponseDTO;
    }

    @Override
    public PatientResponseDTO create(PatientDTO patientDTO) {
        Patient patient = modelMapper.map(patientDTO, Patient.class);
        Patient response = patientRepository.save(patient);
        PatientResponseDTO patientResponseDTO = modelMapper.map(response, PatientResponseDTO.class);
        return patientResponseDTO;
    }

    @Override
    public PatientResponseDTO update(PatientDTO patientDTO, UUID id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if(patient.isEmpty()){
            throw new ModelNotExistsException("Patient not exists");
        }
        modelMapper.map(patientDTO, patient.get());
        Patient response = patientRepository.save(patient.get());
        PatientResponseDTO patientResponseDTO = modelMapper.map(response, PatientResponseDTO.class);
        return patientResponseDTO;
    }

    @Override
    public void delete(UUID id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if(patient.isEmpty()){
            throw new ModelNotExistsException("Patient not exists");
        }
        patientRepository.delete(patient.get());
    }

}
