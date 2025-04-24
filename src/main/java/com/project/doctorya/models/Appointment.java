package com.project.doctorya.models;

import java.security.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    private String id;
    private String reason;
    private Timestamp dateAndHour;
}
