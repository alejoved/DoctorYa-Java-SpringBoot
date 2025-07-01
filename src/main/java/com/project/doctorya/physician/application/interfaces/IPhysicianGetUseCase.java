package com.project.doctorya.physician.application.interfaces;

import java.util.List;
import java.util.UUID;

import com.project.doctorya.physician.domain.models.PhysicianModel;

public interface IPhysicianGetUseCase {
    public List<PhysicianModel> execute();
    public PhysicianModel executeById(UUID id);
    public PhysicianModel executeByIdentification(String identification);
}
