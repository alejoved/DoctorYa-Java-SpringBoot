package com.project.doctorya.appointment.application.usecase;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.doctorya.appointment.application.port.IAppointmentCreateUseCase;
import com.project.doctorya.appointment.domain.model.AppointmentModel;
import com.project.doctorya.appointment.domain.repository.IAppointmentRepository;

public class AppointmentCreateUseCase implements IAppointmentCreateUseCase {

    @Autowired
    private IAppointmentRepository appointmentRepository;

    @Override
    public AppointmentModel execute(AppointmentModel appointmentModel) {
        return appointmentRepository.create(appointmentModel);
    }
    
}
