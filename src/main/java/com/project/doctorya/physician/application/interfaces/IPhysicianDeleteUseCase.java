package com.project.doctorya.physician.application.port;

import java.util.UUID;

public interface IPhysicianDeleteUseCase {
    public void execute(UUID id);
}
