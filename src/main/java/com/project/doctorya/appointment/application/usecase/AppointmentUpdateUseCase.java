package com.project.doctorya.appointment.application.usecase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.doctorya.appointment.application.port.IAppointmentUpdateUseCase;
import com.project.doctorya.appointment.domain.model.AppointmentModel;
import com.project.doctorya.appointment.domain.repository.IAppointmentRepository;

public class AppointmentUpdateUseCase implements IAppointmentUpdateUseCase {

    @Autowired
    private IAppointmentRepository appointmentRepository;

    @Override
    public AppointmentModel execute(AppointmentModel appointmentModel, UUID id) {
        return appointmentRepository.update(appointmentModel, id);
    }
    
}
