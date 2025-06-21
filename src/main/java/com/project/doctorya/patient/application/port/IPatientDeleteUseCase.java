package com.project.doctorya.patient.application.port;

import java.util.UUID;

public interface IPatientDeleteUseCase {
    public void execute(UUID id);
}
