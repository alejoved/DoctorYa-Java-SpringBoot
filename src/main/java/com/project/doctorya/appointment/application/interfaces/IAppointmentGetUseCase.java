package com.project.doctorya.appointment.application.interfaces;

import java.util.List;
import java.util.UUID;

import com.project.doctorya.appointment.domain.models.Appointment;

public interface IAppointmentGetUseCase {
    public List<Appointment> execute();
    public Appointment executeById(UUID id);
}
