package com.project.doctorya.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.project.doctorya.dtos.appointment.AppointmentDTO;
import com.project.doctorya.dtos.appointment.AppointmentResponseDTO;
import com.project.doctorya.services.interf.IAppointmentService;

@Service
public class AppointmentService implements IAppointmentService {

    @Override
    public List<AppointmentResponseDTO> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public AppointmentResponseDTO getById(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public AppointmentResponseDTO create(AppointmentDTO appointmentDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public AppointmentResponseDTO update(AppointmentDTO appointmentDTO, UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
