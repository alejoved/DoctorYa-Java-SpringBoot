package com.project.doctorya.appointment.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AppointmentDTO {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    private Timestamp startDate;
    @NotNull
    private int duration;
    @NotNull
    private String reason;
    @NotNull
    private String patientIdentification;
    @NotNull
    private String physicianIdentification;
}
