package com.project.doctorya.physician.application.usecases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.doctorya.exceptions.EntityNotExistsException;
import com.project.doctorya.physician.application.interfaces.IPhysicianUpdateUseCase;
import com.project.doctorya.physician.domain.models.Physician;
import com.project.doctorya.physician.domain.repositories.IPhysicianRepository;
import com.project.doctorya.shared.Constants;

@Service
public class PhysicianUpdateUseCase implements IPhysicianUpdateUseCase {

    @Autowired
    private IPhysicianRepository physicianRepository;

    @Override
    public Physician execute(Physician physicianModel, UUID id) {
        Physician physicianExists = physicianRepository.getById(id);
        if(physicianExists == null){
            throw new EntityNotExistsException(Constants.physicianNotFound);
        }
        physicianModel.setId(id);
        physicianModel.setAuth(physicianExists.getAuth());
        return physicianRepository.update(physicianModel);
    }
    
}
