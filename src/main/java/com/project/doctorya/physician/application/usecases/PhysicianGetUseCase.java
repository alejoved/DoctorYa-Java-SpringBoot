package com.project.doctorya.physician.application.usecases;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.doctorya.exceptions.EntityNotExistsException;
import com.project.doctorya.physician.application.interfaces.IPhysicianGetUseCase;
import com.project.doctorya.physician.domain.models.PhysicianModel;
import com.project.doctorya.physician.domain.repositories.IPhysicianRepository;
import com.project.doctorya.shared.Constants;

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
        PhysicianModel physicianModel = physicianRepository.getById(id);
        if (physicianModel == null){
            throw new EntityNotExistsException(Constants.physicianNotFound);
        }
        return physicianModel;
    }

    @Override
    public PhysicianModel executeByIdentification(String identification) {
        PhysicianModel physicianModel =  physicianRepository.getByIdentification(identification);
        if (physicianModel == null){
            throw new EntityNotExistsException(Constants.physicianNotFound);
        }
        return physicianModel;
    }
    
}
