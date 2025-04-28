package com.project.doctorya.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.doctorya.dtos.patient.PatientResponseDTO;
import com.project.doctorya.dtos.physician.PhysicianDTO;
import com.project.doctorya.dtos.physician.PhysicianResponseDTO;
import com.project.doctorya.exceptions.ModelNotExistsException;
import com.project.doctorya.models.Patient;
import com.project.doctorya.models.Physician;
import com.project.doctorya.repositories.PatientRepository;
import com.project.doctorya.repositories.PhysicianRepository;
import com.project.doctorya.services.interf.IPhysicianService;

@Service
public class PhysicianService implements IPhysicianService {

    @Autowired
    private PhysicianRepository physicianRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PhysicianResponseDTO> getAll() {
        List<Physician> patient = physicianRepository.findAll();
        List<PhysicianResponseDTO> patientResponseDTO = modelMapper.map(patient, new TypeToken<List<PhysicianResponseDTO>>() {}.getType());
        return patientResponseDTO;
    }

    @Override
    public PhysicianResponseDTO getById(UUID id) {
        Optional<Physician> physician = physicianRepository.findById(id);
        if(physician.isEmpty()){
            throw new ModelNotExistsException("Physician not exists");
        }
        PhysicianResponseDTO patientResponseDTO = modelMapper.map(physician, PhysicianResponseDTO.class);
        return patientResponseDTO;
    }

    @Override
    public PhysicianResponseDTO create(PhysicianDTO physicianDTO) {
        Physician physician = modelMapper.map(physicianDTO, Physician.class);
        Physician response = physicianRepository.save(physician);
        PhysicianResponseDTO physicianResponseDTO = modelMapper.map(response, PhysicianResponseDTO.class);
        return physicianResponseDTO;
    }

    @Override
    public PhysicianResponseDTO update(PhysicianDTO physicianDTO, UUID id) {
        Optional<Physician> physician = physicianRepository.findById(id);
        if(physician.isEmpty()){
            throw new ModelNotExistsException("Physician not exists");
        }
        modelMapper.map(physicianDTO, physician.get());
        Physician response = physicianRepository.save(physician.get());
        PhysicianResponseDTO physicianResponseDTO = modelMapper.map(response, PhysicianResponseDTO.class);
        return physicianResponseDTO;
    }

    @Override
    public void delete(UUID id) {
        Optional<Physician> physician = physicianRepository.findById(id);
        if(physician.isEmpty()){
            throw new ModelNotExistsException("Physician not exists");
        }
        physicianRepository.delete(physician.get());
    }

}
