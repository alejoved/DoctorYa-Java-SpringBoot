package com.project.doctorya.appointment.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.doctorya.appointment.entity.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    @Query("SELECT a FROM Appointment a WHERE a.startDate < :endDate AND a.endDate > :startDate")
    List<Appointment> findOverlapping(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);

}
