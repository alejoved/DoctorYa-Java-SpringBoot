package com.project.doctorya.appointment.application.interfaces;

import java.util.List;
import java.util.UUID;

import com.project.doctorya.appointment.domain.model.AppointmentModel;

public interface IAppointmentGetUseCase {
    public List<AppointmentModel> execute();
    public AppointmentModel executeById(UUID id);
}
