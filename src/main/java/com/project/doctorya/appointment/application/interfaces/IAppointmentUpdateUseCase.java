package com.project.doctorya.appointment.application.interfaces;

import java.util.UUID;

import com.project.doctorya.appointment.domain.models.AppointmentModel;

public interface IAppointmentUpdateUseCase {
    public AppointmentModel execute(AppointmentModel appointmentModel, UUID id);
}
