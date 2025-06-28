package com.project.doctorya.appointment.infrastructure.entity;

import java.sql.Timestamp;
import java.util.UUID;

import com.project.doctorya.patient.infrastructure.entity.Patient;
import com.project.doctorya.physician.infrastructure.entity.Physician;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String reason;
    @Column(nullable = false)
    private int duration;
    @Column(nullable = false)
    private Timestamp startDate;
    @Column(nullable = false)
    private Timestamp endDate;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "physician_id", nullable = false)
    private Physician physician;    
}
