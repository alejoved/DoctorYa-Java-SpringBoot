package com.project.doctorya.physician.application.interfaces;

import com.project.doctorya.physician.domain.models.Physician;

public interface IPhysicianCreateUseCase {
    public Physician execute(Physician physicianModel);
}
