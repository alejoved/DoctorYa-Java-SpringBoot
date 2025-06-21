package com.project.doctorya.appointment.application.port;

import java.util.UUID;

public interface IAppointmentDeleteUseCase {
    public void execute(UUID id);
}
