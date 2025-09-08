package com.project.doctorya.appointment.rest.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AppointmentDto {
    @Schema(description = "Initial date of the appointment", example = "2025-05-10 14:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    private Timestamp startDate;
    @Schema(description = "Appointment duration in minutes")
    @NotNull
    private int duration;
    @Schema(description = "Description about the appointment")
    @NotNull
    private String reason;
    @Schema(description = "Primary identification of the patient")
    @NotNull
    private String patientIdentification;
    @Schema(description = "Primary identification of the physician")
    @NotNull
    private String physicianIdentification;
}
