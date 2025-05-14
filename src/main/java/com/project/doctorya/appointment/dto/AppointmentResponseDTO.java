package com.project.doctorya.appointment.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.doctorya.patient.dto.PatientResponseDTO;
import com.project.doctorya.physician.dto.PhysicianResponseDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AppointmentResponseDTO {
    @Schema(description = "Date for to start medical appointment", example = "2025-05-10 14:00:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime  startDate;
    @Schema(description = "Date for to end medical appointment", example = "2025-05-10 14:20:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;
    @Schema(description = "Description about the appointment")
    private String reason;
    @Schema(description = "Main identification about the patient")
    private PatientResponseDTO patient;
    @Schema(description = "Main identification about the physician")
    private PhysicianResponseDTO physician;
}
