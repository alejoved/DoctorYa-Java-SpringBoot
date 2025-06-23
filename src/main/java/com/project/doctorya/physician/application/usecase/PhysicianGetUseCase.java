package com.project.doctorya.physician.application.usecase;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.doctorya.physician.application.port.IPhysicianGetUseCase;
import com.project.doctorya.physician.domain.model.PhysicianModel;
import com.project.doctorya.physician.domain.repository.IPhysicianRepository;

@Service
public class PhysicianGetUseCase implements IPhysicianGetUseCase {

    @Autowired
    private IPhysicianRepository physicianRepository;

    @Override
    public List<PhysicianModel> execute() {
        return physicianRepository.get();
    }

    @Override
    public PhysicianModel executeById(UUID id) {
        return physicianRepository.getById(id);
    }

    @Override
    public PhysicianModel executeByIdentification(String identification) {
        return physicianRepository.getByIdentification(identification);
    }
    
}
