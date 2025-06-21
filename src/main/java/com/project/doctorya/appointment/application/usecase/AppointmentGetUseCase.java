package com.project.doctorya.appointment.application.usecase;

import java.util.List;
import java.util.UUID;

import com.project.doctorya.appointment.application.port.IAppointmentGetUseCase;
import com.project.doctorya.appointment.domain.model.AppointmentModel;

public class AppointmentGetUseCase implements IAppointmentGetUseCase {

    @Override
    public List<AppointmentModel> execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    @Override
    public AppointmentModel executeById(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'executeById'");
    }
    
}
