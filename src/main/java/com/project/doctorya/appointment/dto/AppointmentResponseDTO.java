package com.project.doctorya.appointment.dto;

import java.sql.Timestamp;

import com.project.doctorya.patient.dto.PatientResponseDTO;
import com.project.doctorya.physician.dto.PhysicianResponseDTO;

import lombok.Data;

@Data
public class AppointmentResponseDTO {
    private Timestamp startDate;
    private Timestamp endDate;
    private String reason;
    private PatientResponseDTO patient;
    private PhysicianResponseDTO physician;
}
