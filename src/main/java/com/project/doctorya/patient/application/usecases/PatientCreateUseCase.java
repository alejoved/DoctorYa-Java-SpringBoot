package com.project.doctorya.patient.application.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.doctorya.auth.domain.model.AuthModel;
import com.project.doctorya.auth.domain.repository.IAuthRepository;
import com.project.doctorya.exceptions.EntityExistsException;
import com.project.doctorya.patient.application.port.IPatientCreateUseCase;
import com.project.doctorya.patient.domain.model.PatientModel;
import com.project.doctorya.patient.domain.repository.IPatientRepository;
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
    public PatientModel execute(PatientModel patientModel) {
        AuthModel authFound = authRepository.getByIdentification(patientModel.getAuthModel().getIdentification());
        if(authFound != null){
            throw new EntityExistsException(Constants.authExists);
        }
        String password = passwordEncoder.encode(patientModel.getAuthModel().getPassword());
        patientModel.getAuthModel().setPassword(password);
        patientModel.getAuthModel().setRole(Role.PATIENT);
        return patientRepository.create(patientModel);
    }
}
