package com.project.doctorya.physician.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.doctorya.auth.entity.Auth;
import com.project.doctorya.auth.repository.AuthRepository;
import com.project.doctorya.common.Constants;
import com.project.doctorya.common.Role;
import com.project.doctorya.exceptions.EntityExistsException;
import com.project.doctorya.exceptions.EntityNotExistsException;
import com.project.doctorya.physician.dto.PhysicianDTO;
import com.project.doctorya.physician.dto.PhysicianResponseDTO;
import com.project.doctorya.physician.entity.Physician;
import com.project.doctorya.physician.repository.PhysicianRepository;

@Service
public class PhysicianService implements IPhysicianService {

    @Autowired
    private PhysicianRepository physicianRepository;

    @Autowired
    private AuthRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
            throw new EntityNotExistsException(Constants.physicianNotFound);
        }
        PhysicianResponseDTO patientResponseDTO = modelMapper.map(physician, PhysicianResponseDTO.class);
        return patientResponseDTO;
    }

    @Override
    public PhysicianResponseDTO create(PhysicianDTO physicianDTO) {
        Optional<Auth> userExists = userRepository.findByIdentification(physicianDTO.getIdentification());
        if(userExists.isPresent()){
            throw new EntityExistsException(Constants.authExists);
        }
        Auth auth = modelMapper.map(physicianDTO, Auth.class);
        String password = passwordEncoder.encode(physicianDTO.getPassword());
        auth.setPassword(password);
        auth.setRol(Role.PHYSICIAN);
        Physician physician = modelMapper.map(physicianDTO, Physician.class);
        physician.setAuth(auth);
        Physician response = physicianRepository.save(physician);
        PhysicianResponseDTO physicianResponseDTO = modelMapper.map(response, PhysicianResponseDTO.class);
        return physicianResponseDTO;
    }

    @Override
    public PhysicianResponseDTO update(PhysicianDTO physicianDTO, UUID id) {
        Optional<Physician> physician = physicianRepository.findById(id);
        if(physician.isEmpty()){
            throw new EntityNotExistsException(Constants.physicianNotFound);
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
            throw new EntityNotExistsException(Constants.physicianNotFound);
        }
        physicianRepository.delete(physician.get());
    }
}
