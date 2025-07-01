package com.project.doctorya.appointment.domain.repositories;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import com.project.doctorya.appointment.domain.models.AppointmentModel;

public interface IAppointmentRepository {
    public List<AppointmentModel> get();
    public AppointmentModel getById(UUID id);
    public List<AppointmentModel> getOverLapping(Timestamp startDate, Timestamp endDate, String physicianIdentification);
    public AppointmentModel create(AppointmentModel appointmentModel);
    public AppointmentModel update(AppointmentModel appointmentModel);
    public void delete(UUID id);
}
