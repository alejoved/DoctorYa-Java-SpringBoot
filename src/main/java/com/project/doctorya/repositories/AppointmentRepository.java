package com.project.doctorya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.doctorya.models.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String> {

}
