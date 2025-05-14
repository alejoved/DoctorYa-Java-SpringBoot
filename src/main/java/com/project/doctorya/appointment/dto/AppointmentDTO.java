package com.project.doctorya.appointment.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AppointmentDTO {
    @Schema(description = "Date for to start medical appointment", example = "2025-05-10 14:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    private Timestamp startDate;
    @Schema(description = "Appointment duration in minutes", example = "30")
    @NotNull
    private int duration;
    @Schema(description = "Description about the appointment", example = "Blood test review")
    @NotNull
    private String reason;
    @Schema(description = "Main identification about the patient", example = "1053856098")
    @NotNull
    private String patientIdentification;
    @Schema(description = "Main identification about the physician", example = "1053856097")
    @NotNull
    private String physicianIdentification;
}
