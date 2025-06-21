package com.project.doctorya.appointment.application.port;

import com.project.doctorya.appointment.domain.model.AppointmentModel;

public interface IAppointmentUpdateUseCase {
    public AppointmentModel execute(AppointmentModel appointmentModel);
}
