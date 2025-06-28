package com.project.doctorya.appointment.application.interfaces;

import java.util.UUID;

public interface IAppointmentDeleteUseCase {
    public void execute(UUID id);
}
