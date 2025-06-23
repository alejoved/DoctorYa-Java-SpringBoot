package com.project.doctorya.physician.application.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.doctorya.auth.domain.model.AuthModel;
import com.project.doctorya.auth.domain.repository.IAuthRepository;
import com.project.doctorya.exceptions.EntityExistsException;
import com.project.doctorya.physician.application.port.IPhysicianCreateUseCase;
import com.project.doctorya.physician.domain.model.PhysicianModel;
import com.project.doctorya.physician.domain.repository.IPhysicianRepository;
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
