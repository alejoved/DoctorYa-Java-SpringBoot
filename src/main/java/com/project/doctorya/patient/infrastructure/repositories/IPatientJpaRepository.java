package com.project.doctorya.patient.infrastructure.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.doctorya.patient.infrastructure.entities.PatientEntity;

@Repository
public interface IPatientJpaRepository extends JpaRepository<PatientEntity, UUID> {
    Optional<PatientEntity> findByAuthEntityIdentification(String identification);
}
