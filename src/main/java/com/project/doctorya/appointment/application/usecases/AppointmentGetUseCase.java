package com.project.doctorya.appointment.application.usecases;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.doctorya.appointment.application.interfaces.IAppointmentGetUseCase;
import com.project.doctorya.appointment.domain.models.AppointmentModel;
import com.project.doctorya.appointment.domain.repositories.IAppointmentRepository;
import com.project.doctorya.exceptions.EntityNotExistsException;
import com.project.doctorya.shared.Constants;

@Service
public class AppointmentGetUseCase implements IAppointmentGetUseCase {

    @Autowired
    private IAppointmentRepository appointmentRepository;

    @Override
    public List<AppointmentModel> execute() {
        List<AppointmentModel> appointments = appointmentRepository.get();
        return appointments;
    }

    @Override
    public AppointmentModel executeById(UUID id) {
        AppointmentModel appointmentModel = appointmentRepository.getById(id);
        if (appointmentModel == null){
            throw new EntityNotExistsException(Constants.appointmentNotFound);
        }
        return appointmentModel;
    }
    
}
