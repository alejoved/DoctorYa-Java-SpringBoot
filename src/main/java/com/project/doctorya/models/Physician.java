package com.project.doctorya.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "physician")
public class Physician {
    @Id
    private String id;
    private String name;
    private String speciality;
    private String password;

    @OneToOne
    @JoinColumn(name = "identificacion")
    private User user;
}
