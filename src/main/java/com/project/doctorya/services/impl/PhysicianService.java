package com.project.doctorya.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.project.doctorya.dtos.physician.PhysicianDTO;
import com.project.doctorya.dtos.physician.PhysicianResponseDTO;
import com.project.doctorya.exceptions.ModelExistsException;
import com.project.doctorya.exceptions.ModelNotExistsException;
import com.project.doctorya.models.Physician;
import com.project.doctorya.models.User;
import com.project.doctorya.repositories.PhysicianRepository;
import com.project.doctorya.repositories.UserRepository;
import com.project.doctorya.services.interf.IPhysicianService;
import com.project.doctorya.utils.Constants;
import com.project.doctorya.utils.Rol;

@Service
public class PhysicianService implements IPhysicianService {

    @Autowired
    private PhysicianRepository physicianRepository;

    @Autowired
    private UserRepository userRepository;

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
            throw new ModelNotExistsException(Constants.physicianNotFound);
        }
        PhysicianResponseDTO patientResponseDTO = modelMapper.map(physician, PhysicianResponseDTO.class);
        return patientResponseDTO;
    }

    @Override
    public PhysicianResponseDTO create(PhysicianDTO physicianDTO) {
        Optional<User> userExists = userRepository.findByIdentification(physicianDTO.getIdentification());
        if(userExists.isPresent()){
            throw new ModelExistsException(Constants.userExists);
        }
        User user = modelMapper.map(physicianDTO, User.class);
        String password = passwordEncoder.encode(physicianDTO.getPassword());
        user.setPassword(password);
        user.setRol(Rol.PHYSICIAN);
        Physician physician = modelMapper.map(physicianDTO, Physician.class);
        physician.setUser(user);
        Physician response = physicianRepository.save(physician);
        PhysicianResponseDTO physicianResponseDTO = modelMapper.map(response, PhysicianResponseDTO.class);
        return physicianResponseDTO;
    }

    @Override
    public PhysicianResponseDTO update(PhysicianDTO physicianDTO, UUID id) {
        Optional<Physician> physician = physicianRepository.findById(id);
        if(physician.isEmpty()){
            throw new ModelNotExistsException(Constants.physicianNotFound);
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
            throw new ModelNotExistsException(Constants.physicianNotFound);
        }
        physicianRepository.delete(physician.get());
    }
}
