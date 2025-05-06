package com.project.doctorya.physician.entity;

import java.util.UUID;

import com.project.doctorya.auth.entity.Auth;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "physician")
public class Physician {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String code;
    @Column(nullable = false)
    private String speciality;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "identification")
    private Auth user;

    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getCode() {
        return code;
    }
    public String getSpeciality() {
        return speciality;
    }
    public Auth getUser() {
        return user;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
    public void setUser(Auth user) {
        this.user = user;
    }
}
