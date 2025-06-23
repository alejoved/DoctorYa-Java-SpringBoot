package com.project.doctorya.physician.application.usecase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.doctorya.physician.application.port.IPhysicianUpdateUseCase;
import com.project.doctorya.physician.domain.model.PhysicianModel;
import com.project.doctorya.physician.domain.repository.IPhysicianRepository;

@Service
public class PhysicianUpdateUseCase implements IPhysicianUpdateUseCase {

    @Autowired
    private IPhysicianRepository physicianRepository;

    @Override
    public PhysicianModel execute(PhysicianModel physicianModel, UUID id) {
        return physicianRepository.update(physicianModel, id);
    }
    
}
