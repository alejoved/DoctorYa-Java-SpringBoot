package com.project.doctorya.patient.infrastructure.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.doctorya.patient.infrastructure.entity.Patient;

@Repository
public interface IPatientJpaRepository extends JpaRepository<Patient, UUID> {
    Optional<Patient> findByAuthIdentification(String identification);
}
