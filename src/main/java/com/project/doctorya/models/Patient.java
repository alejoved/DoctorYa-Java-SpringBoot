package com.project.doctorya.models;

import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String insurance;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "identification")
    private User user;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInsurance() {
        return insurance;
    }

    public User getUser() {
        return user;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
