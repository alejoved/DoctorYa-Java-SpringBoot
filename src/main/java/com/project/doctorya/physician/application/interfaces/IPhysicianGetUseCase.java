package com.project.doctorya.physician.application.interfaces;

import java.util.List;
import java.util.UUID;

import com.project.doctorya.physician.domain.models.Physician;

public interface IPhysicianGetUseCase {
    public List<Physician> execute();
    public Physician executeById(UUID id);
    public Physician executeByIdentification(String identification);
}
