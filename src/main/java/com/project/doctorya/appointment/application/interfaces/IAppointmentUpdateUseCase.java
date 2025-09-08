package com.project.doctorya.appointment.application.interfaces;

import java.util.UUID;

import com.project.doctorya.appointment.domain.models.Appointment;

public interface IAppointmentUpdateUseCase {
    public Appointment execute(Appointment appointmentModel, UUID id);
}
