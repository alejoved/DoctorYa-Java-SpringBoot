package com.project.doctorya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.doctorya.models.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {

}
