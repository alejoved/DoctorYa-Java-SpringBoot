package com.project.doctorya.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.project.doctorya.dtos.patient.PatientDTO;
import com.project.doctorya.dtos.patient.PatientResponseDTO;
import com.project.doctorya.exceptions.ModelExistsException;
import com.project.doctorya.exceptions.ModelNotExistsException;
import com.project.doctorya.models.Patient;
import com.project.doctorya.models.User;
import com.project.doctorya.repositories.PatientRepository;
import com.project.doctorya.repositories.UserRepository;
import com.project.doctorya.services.interf.IPatientService;
import com.project.doctorya.utils.Constants;
import com.project.doctorya.utils.Rol;

@Service
public class PatientService implements IPatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserRepository userRepository;

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
            Optional<User> userFound = userRepository.findByIdentification(patientDTO.getIdentification());
            if(userFound.isPresent()){
                throw new ModelExistsException(Constants.userExists);
            }
            User user = modelMapper.map(patientDTO, User.class);
            String password = passwordEncoder.encode(patientDTO.getPassword());
            user.setPassword(password);
            user.setRol(Rol.PATIENT);
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
