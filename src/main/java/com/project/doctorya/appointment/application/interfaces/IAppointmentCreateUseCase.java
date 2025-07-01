package com.project.doctorya.appointment.application.interfaces;

import com.project.doctorya.appointment.domain.models.AppointmentModel;

public interface IAppointmentCreateUseCase {
    public AppointmentModel execute(AppointmentModel appointmentModel);
}
