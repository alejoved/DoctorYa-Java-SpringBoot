package com.project.doctorya.physician.domain.repository;
import java.util.List;
import java.util.UUID;

import com.project.doctorya.physician.domain.model.PhysicianModel;

public interface IPhysicianRepository {
    public List<PhysicianModel> get();
    public PhysicianModel getById(UUID id);
    public PhysicianModel getByIdentification(String identification);
    public PhysicianModel create(PhysicianModel patientModel);
    public PhysicianModel update(PhysicianModel patientModel, UUID id);
    public void delete(UUID id);
}
