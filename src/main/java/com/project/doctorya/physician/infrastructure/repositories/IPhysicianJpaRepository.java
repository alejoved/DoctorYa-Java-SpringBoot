package com.project.doctorya.physician.infrastructure.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.doctorya.physician.infrastructure.entities.Physician;

@Repository
public interface IPhysicianJpaRepository extends JpaRepository<Physician, UUID> {
    Optional<Physician> findByAuthIdentification(String identification);

}
