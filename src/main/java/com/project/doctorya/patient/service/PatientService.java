package com.project.doctorya.patient.service;

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
            throw new EntityNotExistsException(Constants.patientNotFound);
        }
        PatientResponseDTO patientResponseDTO = modelMapper.map(patient, PatientResponseDTO.class);
        return patientResponseDTO;
    }

    @Override
    public PatientResponseDTO create(PatientDTO patientDTO) {
        Optional<Auth> userFound = userRepository.findByIdentification(patientDTO.getIdentification());
        if(userFound.isPresent()){
            throw new EntityExistsException(Constants.authExists);
        }
        Auth auth = modelMapper.map(patientDTO, Auth.class);
        String password = passwordEncoder.encode(patientDTO.getPassword());
        auth.setPassword(password);
        auth.setRol(Role.PATIENT);
        Patient patient = modelMapper.map(patientDTO, Patient.class);
        patient.setAuth(auth);
        Patient response = patientRepository.save(patient);
        PatientResponseDTO patientResponseDTO = modelMapper.map(response, PatientResponseDTO.class);
        return patientResponseDTO; 
    }

    @Override
    public PatientResponseDTO update(PatientDTO patientDTO, UUID id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if(patient.isEmpty()){
            throw new EntityNotExistsException(Constants.patientNotFound);
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
            throw new EntityNotExistsException(Constants.appointmentNotFound);
        }
        patientRepository.delete(patient.get());
    }

}
