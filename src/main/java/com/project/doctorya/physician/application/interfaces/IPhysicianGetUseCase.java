package com.project.doctorya.physician.application.port;

import java.util.List;
import java.util.UUID;
import com.project.doctorya.physician.domain.model.PhysicianModel;

public interface IPhysicianGetUseCase {
    public List<PhysicianModel> execute();
    public PhysicianModel executeById(UUID id);
    public PhysicianModel executeByIdentification(String identification);
}
