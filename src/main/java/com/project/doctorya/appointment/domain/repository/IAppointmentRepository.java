package com.project.doctorya.appointment.domain.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import com.project.doctorya.appointment.domain.model.AppointmentModel;

public interface IAppointmentRepository {
    public List<AppointmentModel> get();
    public AppointmentModel getById(UUID id);
    public List<AppointmentModel> getOverLapping(Timestamp startDate, Timestamp endDate, String physicianIdentification);
    public AppointmentModel create(AppointmentModel appointmentModel);
    public AppointmentModel update(AppointmentModel appointmentModel, UUID id);
    public void delete(UUID id);
}
