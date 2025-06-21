package com.project.doctorya.physician.application.port;

import java.util.UUID;

import com.project.doctorya.physician.domain.model.PhysicianModel;

public interface IPhysicianUpdateUseCase {
    public PhysicianModel execute(PhysicianModel physicianModel, UUID id);
}
