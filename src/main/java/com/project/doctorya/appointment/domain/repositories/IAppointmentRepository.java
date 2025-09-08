package com.project.doctorya.appointment.domain.repositories;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import com.project.doctorya.appointment.domain.models.Appointment;

public interface IAppointmentRepository {
    public List<Appointment> get();
    public Appointment getById(UUID id);
    public List<Appointment> getOverLapping(Timestamp startDate, Timestamp endDate, String physicianIdentification);
    public Appointment create(Appointment appointmentModel);
    public Appointment update(Appointment appointmentModel);
    public void delete(UUID id);
}
