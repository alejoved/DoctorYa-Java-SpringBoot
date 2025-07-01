package com.project.doctorya.patient.infrastructure.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.doctorya.patient.infrastructure.entities.Patient;

@Repository
public interface IPatientJpaRepository extends JpaRepository<Patient, UUID> {
    Optional<Patient> findByAuthIdentification(String identification);
}
