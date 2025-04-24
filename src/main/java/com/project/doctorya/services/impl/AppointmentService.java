package com.project.doctorya.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.doctorya.dtos.AppointmentDTO;
import com.project.doctorya.services.interf.IAppointmentService;

@Service
public class AppointmentService implements IAppointmentService {

    @Override
    public List<AppointmentDTO> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public AppointmentDTO getById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public AppointmentDTO create(AppointmentDTO obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public AppointmentDTO update(AppointmentDTO obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
