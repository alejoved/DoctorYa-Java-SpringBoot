package com.project.doctorya.appointment.application.usecase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.doctorya.appointment.application.port.IAppointmentUpdateUseCase;
import com.project.doctorya.appointment.domain.model.AppointmentModel;
import com.project.doctorya.appointment.domain.repository.IAppointmentRepository;

@Service
public class AppointmentUpdateUseCase implements IAppointmentUpdateUseCase {

    @Autowired
    private IAppointmentRepository appointmentRepository;

    @Override
    public AppointmentModel execute(AppointmentModel appointmentModel, UUID id) {
        return appointmentRepository.update(appointmentModel, id);
    }
    
}
