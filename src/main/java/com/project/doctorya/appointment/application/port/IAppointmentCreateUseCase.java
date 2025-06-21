package com.project.doctorya.appointment.application.port;

import com.project.doctorya.appointment.domain.model.AppointmentModel;

public interface IAppointmentCreateUseCase {
    public AppointmentModel execute(AppointmentModel appointmentModel);
}
