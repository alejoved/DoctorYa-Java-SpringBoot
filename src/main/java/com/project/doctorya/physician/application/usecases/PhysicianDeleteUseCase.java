package com.project.doctorya.physician.application.usecases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.doctorya.physician.application.interfaces.IPhysicianDeleteUseCase;
import com.project.doctorya.physician.domain.repositories.IPhysicianRepository;

@Service
public class PhysicianDeleteUseCase implements IPhysicianDeleteUseCase {

    @Autowired
    private IPhysicianRepository physicianRepository;

    @Override
    public void execute(UUID id) {
        physicianRepository.delete(id);
    }
    
}
