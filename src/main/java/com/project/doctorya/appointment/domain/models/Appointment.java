package com.project.doctorya.appointment.domain.models;

import java.sql.Timestamp;
import java.util.UUID;

import com.project.doctorya.patient.domain.models.Patient;
import com.project.doctorya.physician.domain.models.Physician;

import lombok.Data;

@Data
public class Appointment {

    private UUID id;
    private String reason;
    private int duration;
    private Timestamp startDate;
    private Timestamp endDate;
    private Patient patient;
    private Physician physician;   
}
