package com.project.doctorya.physician.domain.repositories;
import java.util.List;
import java.util.UUID;

import com.project.doctorya.physician.domain.models.Physician;

public interface IPhysicianRepository {
    public List<Physician> get();
    public Physician getById(UUID id);
    public Physician getByIdentification(String identification);
    public Physician create(Physician patientModel);
    public Physician update(Physician patientModel);
    public void delete(UUID id);
}
