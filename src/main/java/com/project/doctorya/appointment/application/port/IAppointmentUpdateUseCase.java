package com.project.doctorya.appointment.application.port;

import java.util.UUID;

import com.project.doctorya.appointment.domain.model.AppointmentModel;

public interface IAppointmentUpdateUseCase {
    public AppointmentModel execute(AppointmentModel appointmentModel, UUID id);
}
