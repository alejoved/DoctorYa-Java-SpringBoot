package com.project.doctorya.physician.application.interfaces;

import java.util.UUID;

import com.project.doctorya.physician.domain.models.PhysicianModel;

public interface IPhysicianUpdateUseCase {
    public PhysicianModel execute(PhysicianModel physicianModel, UUID id);
}
