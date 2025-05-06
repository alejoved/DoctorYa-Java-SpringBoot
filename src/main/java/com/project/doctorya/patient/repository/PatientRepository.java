package com.project.doctorya.patient.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.doctorya.patient.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {

}
