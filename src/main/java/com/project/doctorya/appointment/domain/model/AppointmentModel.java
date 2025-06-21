package com.project.doctorya.appointment.domain.model;

import java.sql.Timestamp;
import java.util.UUID;

import com.project.doctorya.patient.domain.model.PatientModel;
import com.project.doctorya.physician.domain.model.PhysicianModel;

import lombok.Data;

@Data
public class AppointmentModel {

    private UUID id;
    private String reason;
    private int duration;
    private Timestamp startDate;
    private Timestamp endDate;
    private PatientModel patientModel;
    private PhysicianModel physicianModel;   
}
