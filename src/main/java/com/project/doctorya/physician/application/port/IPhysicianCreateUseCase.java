package com.project.doctorya.physician.application.port;

import com.project.doctorya.physician.domain.model.PhysicianModel;

public interface IPhysicianCreateUseCase {
    public PhysicianModel execute(PhysicianModel physicianModel);
}
