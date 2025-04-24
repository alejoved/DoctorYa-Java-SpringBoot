package com.project.doctorya.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "physician")
public class Physician {
    @Id
    private String id;
    private String identification;
    private String speciality;
    private String password;
}
