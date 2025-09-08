package com.project.doctorya.patient.application.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.doctorya.auth.domain.models.Auth;
import com.project.doctorya.auth.domain.repositories.IAuthRepository;
import com.project.doctorya.exceptions.EntityExistsException;
import com.project.doctorya.patient.application.interfaces.IPatientCreateUseCase;
import com.project.doctorya.patient.domain.models.Patient;
import com.project.doctorya.patient.domain.repositories.IPatientRepository;
import com.project.doctorya.shared.Constants;
import com.project.doctorya.shared.Role;

@Service
public class PatientCreateUseCase implements IPatientCreateUseCase {

    @Autowired
    private IPatientRepository patientRepository;

    @Autowired
    private IAuthRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Patient execute(Patient patient) {
        Auth authFound = authRepository.getByIdentification(patient.getAuth().getIdentification());
        if(authFound != null){
            throw new EntityExistsException(Constants.authExists);
        }
        String password = passwordEncoder.encode(patient.getAuth().getPassword());
        patient.getAuth().setPassword(password);
        patient.getAuth().setRole(Role.PATIENT);
        return patientRepository.create(patient);
    }
}
