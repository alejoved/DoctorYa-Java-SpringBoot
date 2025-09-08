package com.project.doctorya.physician.application.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.doctorya.auth.domain.models.Auth;
import com.project.doctorya.auth.domain.repositories.IAuthRepository;
import com.project.doctorya.exceptions.EntityExistsException;
import com.project.doctorya.physician.application.interfaces.IPhysicianCreateUseCase;
import com.project.doctorya.physician.domain.models.Physician;
import com.project.doctorya.physician.domain.repositories.IPhysicianRepository;
import com.project.doctorya.shared.Constants;
import com.project.doctorya.shared.Role;

@Service
public class PhysicianCreateUseCase implements IPhysicianCreateUseCase {

    @Autowired
    private IPhysicianRepository physicianRepository;

    @Autowired
    private IAuthRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Physician execute(Physician physician) {
        Auth authFound = authRepository.getByIdentification(physician.getAuth().getIdentification());
        if(authFound != null){
            throw new EntityExistsException(Constants.authExists);
        }
        String password = passwordEncoder.encode(physician.getAuth().getPassword());
        physician.getAuth().setPassword(password);
        physician.getAuth().setRole(Role.PHYSICIAN);
        return physicianRepository.create(physician);
    }
    
}
