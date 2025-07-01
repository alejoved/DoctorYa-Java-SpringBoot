package com.project.doctorya.appointment.infrastructure.repositories;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.doctorya.appointment.infrastructure.entities.Appointment;

@Repository
public interface IAppointmentJpaRepository extends JpaRepository<Appointment, UUID> {
    @Query("select a from Appointment a where a.startDate < :endDate and a.endDate > :startDate and a.physician.auth.identification = :physicianIdentification")
    List<Appointment> findOverlapping(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate, @Param("physicianIdentification") String physicianIdentification);

}
