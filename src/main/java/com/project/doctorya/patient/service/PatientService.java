package com.project.doctorya.patient.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.doctorya.auth.entity.Auth;
import com.project.doctorya.auth.repository.AuthRepository;
import com.project.doctorya.common.Constants;
import com.project.doctorya.common.Role;
import com.project.doctorya.exceptions.ModelExistsException;
import com.project.doctorya.exceptions.ModelNotExistsException;
import com.project.doctorya.patient.dto.PatientDTO;
import com.project.doctorya.patient.dto.PatientResponseDTO;
import com.project.doctorya.patient.entity.Patient;
import com.project.doctorya.patient.repository.PatientRepository;

@Service
public class PatientService implements IPatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AuthRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
            throw new ModelNotExistsException(Constants.patientNotFound);
        }
        PatientResponseDTO patientResponseDTO = modelMapper.map(patient, PatientResponseDTO.class);
        return patientResponseDTO;
    }

    @Override
    public PatientResponseDTO create(PatientDTO patientDTO) {
        try {
            Optional<Auth> userFound = userRepository.findByIdentification(patientDTO.getIdentification());
            if(userFound.isPresent()){
                throw new ModelExistsException(Constants.userExists);
            }
            Auth user = modelMapper.map(patientDTO, Auth.class);
            String password = passwordEncoder.encode(patientDTO.getPassword());
            user.setPassword(password);
            user.setRol(Role.PATIENT);
            Patient patient = modelMapper.map(patientDTO, Patient.class);
            patient.setUser(user);
            Patient response = patientRepository.save(patient);
            PatientResponseDTO patientResponseDTO = modelMapper.map(response, PatientResponseDTO.class);
            return patientResponseDTO;
        } catch(DataIntegrityViolationException ex){
            throw new ModelExistsException(Constants.userExists);
        }   
    }

    @Override
    public PatientResponseDTO update(PatientDTO patientDTO, UUID id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if(patient.isEmpty()){
            throw new ModelNotExistsException(Constants.patientNotFound);
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
            throw new ModelNotExistsException(Constants.appointmentNotFound);
        }
        patientRepository.delete(patient.get());
    }

}
