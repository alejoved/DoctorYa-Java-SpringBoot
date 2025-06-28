package com.project.doctorya.appointment.application.usecase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.doctorya.appointment.application.port.IAppointmentDeleteUseCase;
import com.project.doctorya.appointment.domain.model.AppointmentModel;
import com.project.doctorya.appointment.domain.repository.IAppointmentRepository;
import com.project.doctorya.exceptions.EntityNotExistsException;
import com.project.doctorya.shared.Constants;

@Service
public class AppointmentDeleteUseCase implements IAppointmentDeleteUseCase {

    @Autowired
    private IAppointmentRepository appointmentRepository;

    @Override
    public void execute(UUID id) {
        AppointmentModel appointmentModel = appointmentRepository.getById(id);
        if(appointmentModel == null){
            throw new EntityNotExistsException(Constants.appointmentNotFound);
        }
        appointmentRepository.delete(id);
    }
    
}
