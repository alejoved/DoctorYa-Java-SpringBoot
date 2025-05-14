package com.project.doctorya.appointment.dto;

import java.sql.Timestamp;

import com.project.doctorya.patient.dto.PatientResponseDTO;
import com.project.doctorya.physician.dto.PhysicianResponseDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AppointmentResponseDTO {
    @Schema(description = "Date for to start medical appointment", example = "2025-05-10 14:00:00")
    private Timestamp startDate;
    @Schema(description = "Date for to end medical appointment", example = "2025-05-10 14:20:00")
    private Timestamp endDate;
    @Schema(description = "Description about the appointment", example = "Blood test review")
    private String reason;
    @Schema(description = "Main identification about the patient", example = "1053856098")
    private PatientResponseDTO patient;
    @Schema(description = "Main identification about the physician", example = "1053856097")
    private PhysicianResponseDTO physician;
}
