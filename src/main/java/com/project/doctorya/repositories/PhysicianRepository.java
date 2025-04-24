package com.project.doctorya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.doctorya.models.Physician;

@Repository
public interface PhysicianRepository extends JpaRepository<Physician, String> {

}
