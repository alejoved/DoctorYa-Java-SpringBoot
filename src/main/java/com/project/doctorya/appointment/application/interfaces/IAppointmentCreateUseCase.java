package com.project.doctorya.appointment.application.interfaces;

import com.project.doctorya.appointment.domain.models.Appointment;

public interface IAppointmentCreateUseCase {
    public Appointment execute(Appointment appointmentModel);
}
