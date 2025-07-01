package com.project.doctorya.physician.application.interfaces;

import com.project.doctorya.physician.domain.models.PhysicianModel;

public interface IPhysicianCreateUseCase {
    public PhysicianModel execute(PhysicianModel physicianModel);
}
