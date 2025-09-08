package com.project.doctorya.appointment.infrastructure.repositories;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.doctorya.appointment.infrastructure.entities.AppointmentEntity;

@Repository
public interface IAppointmentJpaRepository extends JpaRepository<AppointmentEntity, UUID> {
    @Query("select a from AppointmentEntity a where a.startDate < :endDate and a.endDate > :startDate and a.physicianEntity.authEntity.identification = :physicianIdentification")
    List<AppointmentEntity> findOverlapping(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate, @Param("physicianIdentification") String physicianIdentification);

}
