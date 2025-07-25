package com.project.doctorya.physician.domain.repositories;
import java.util.List;
import java.util.UUID;

import com.project.doctorya.physician.domain.models.PhysicianModel;

public interface IPhysicianRepository {
    public List<PhysicianModel> get();
    public PhysicianModel getById(UUID id);
    public PhysicianModel getByIdentification(String identification);
    public PhysicianModel create(PhysicianModel patientModel);
    public PhysicianModel update(PhysicianModel patientModel);
    public void delete(UUID id);
}
