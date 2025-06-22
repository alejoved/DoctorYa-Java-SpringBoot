package com.project.doctorya.appointment.application.usecase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.doctorya.appointment.application.port.IAppointmentDeleteUseCase;
import com.project.doctorya.appointment.domain.repository.IAppointmentRepository;

public class AppointmentDeleteUseCase implements IAppointmentDeleteUseCase {

    @Autowired
    private IAppointmentRepository appointmentRepository;

    @Override
    public void execute(UUID id) {
        appointmentRepository.delete(id);
    }
    
}
