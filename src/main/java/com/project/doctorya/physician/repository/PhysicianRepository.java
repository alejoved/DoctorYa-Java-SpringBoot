package com.project.doctorya.physician.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.doctorya.physician.entity.Physician;

@Repository
public interface PhysicianRepository extends JpaRepository<Physician, UUID> {

}
