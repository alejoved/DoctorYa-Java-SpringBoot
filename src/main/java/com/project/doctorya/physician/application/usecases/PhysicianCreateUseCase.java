package com.project.doctorya.physician.application.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.doctorya.auth.domain.models.AuthModel;
import com.project.doctorya.auth.domain.repositories.IAuthRepository;
import com.project.doctorya.exceptions.EntityExistsException;
import com.project.doctorya.physician.application.interfaces.IPhysicianCreateUseCase;
import com.project.doctorya.physician.domain.models.PhysicianModel;
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
    public PhysicianModel execute(PhysicianModel physicianModel) {
        AuthModel authFound = authRepository.getByIdentification(physicianModel.getAuthModel().getIdentification());
        if(authFound != null){
            throw new EntityExistsException(Constants.authExists);
        }
        String password = passwordEncoder.encode(physicianModel.getAuthModel().getPassword());
        physicianModel.getAuthModel().setPassword(password);
        physicianModel.getAuthModel().setRole(Role.PHYSICIAN);
        return physicianRepository.create(physicianModel);
    }
    
}
