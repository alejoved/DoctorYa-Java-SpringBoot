package com.project.doctorya.physician.application.interfaces;

import java.util.UUID;

import com.project.doctorya.physician.domain.models.Physician;

public interface IPhysicianUpdateUseCase {
    public Physician execute(Physician physicianModel, UUID id);
}
