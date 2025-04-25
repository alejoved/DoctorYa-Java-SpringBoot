package com.project.doctorya.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    private String id;
    private String name;

    @OneToOne
    @JoinColumn(name = "identificacion")
    private User user;

}
