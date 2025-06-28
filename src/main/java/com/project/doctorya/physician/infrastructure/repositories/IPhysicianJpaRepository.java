package com.project.doctorya.physician.infrastructure.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.doctorya.physician.infrastructure.entity.Physician;

@Repository
public interface IPhysicianJpaRepository extends JpaRepository<Physician, UUID> {
    Optional<Physician> findByAuthIdentification(String identification);

}
