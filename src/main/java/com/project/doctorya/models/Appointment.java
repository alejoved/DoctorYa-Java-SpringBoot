package com.project.doctorya.models;

import java.security.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue
    private String id;
    @Column(nullable = false)
    private String reason;
    @Column(nullable = false)
    private Timestamp dateAndHour;

    @OneToOne
    @JoinColumn(name = "id")
    private Physician physician;

    @OneToOne
    @JoinColumn(name = "id")
    private Patient patient;

    public String getId() {
        return id;
    }

    public String getReason() {
        return reason;
    }

    public Timestamp getDateAndHour() {
        return dateAndHour;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public void setDateAndHour(Timestamp dateAndHour) {
        this.dateAndHour = dateAndHour;
    }
}
