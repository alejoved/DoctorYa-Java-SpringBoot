package com.project.doctorya.physician.application.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import com.project.doctorya.physician.application.port.IPhysicianCreateUseCase;
import com.project.doctorya.physician.domain.model.PhysicianModel;
import com.project.doctorya.physician.domain.repository.IPhysicianRepository;

public class PhysicianCreateUseCase implements IPhysicianCreateUseCase {

    @Autowired
    private IPhysicianRepository physicianRepository;

    @Override
    public PhysicianModel execute(PhysicianModel physicianModel) {
        return physicianRepository.create(physicianModel);
    }
    
}
