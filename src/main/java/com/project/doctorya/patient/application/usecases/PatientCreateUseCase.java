package com.project.doctorya.patient.application.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.doctorya.auth.domain.models.AuthModel;
import com.project.doctorya.auth.domain.repositories.IAuthRepository;
import com.project.doctorya.exceptions.EntityExistsException;
import com.project.doctorya.patient.application.interfaces.IPatientCreateUseCase;
import com.project.doctorya.patient.domain.models.PatientModel;
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
