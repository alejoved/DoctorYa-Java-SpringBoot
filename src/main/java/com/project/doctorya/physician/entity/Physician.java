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
import lombok.Data;

@Data
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
    @JoinColumn(name = "auth_id")
    private Auth auth;
}
