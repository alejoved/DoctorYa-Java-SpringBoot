package com.project.doctorya.physician.application.usecases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.doctorya.exceptions.EntityNotExistsException;
import com.project.doctorya.physician.application.interfaces.IPhysicianUpdateUseCase;
import com.project.doctorya.physician.domain.models.PhysicianModel;
import com.project.doctorya.physician.domain.repositories.IPhysicianRepository;
import com.project.doctorya.shared.Constants;

@Service
public class PhysicianUpdateUseCase implements IPhysicianUpdateUseCase {

    @Autowired
    private IPhysicianRepository physicianRepository;

    @Override
    public PhysicianModel execute(PhysicianModel physicianModel, UUID id) {
        PhysicianModel physicianExists = physicianRepository.getById(id);
        if(physicianExists == null){
            throw new EntityNotExistsException(Constants.physicianNotFound);
        }
        physicianModel.setId(id);
        physicianModel.setAuthModel(physicianExists.getAuthModel());
        return physicianRepository.update(physicianModel);
    }
    
}
