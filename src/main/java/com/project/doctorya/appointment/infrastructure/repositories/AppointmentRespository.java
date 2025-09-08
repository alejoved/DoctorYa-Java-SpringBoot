package com.project.doctorya.appointment.infrastructure.repositories;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.doctorya.appointment.domain.models.Appointment;
import com.project.doctorya.appointment.domain.repositories.IAppointmentRepository;
import com.project.doctorya.appointment.infrastructure.entities.AppointmentEntity;
import com.project.doctorya.appointment.infrastructure.mappers.AppointmentMapper;
import com.project.doctorya.exceptions.EntityNotExistsException;
import com.project.doctorya.shared.Constants;

@Repository
@Primary
public class AppointmentRespository implements IAppointmentRepository{

    @Autowired
    private IAppointmentJpaRepository appointmentRepository;

    @Override
    public List<Appointment> get() {
        List<AppointmentEntity> appointmentEntity = appointmentRepository.findAll();
        return appointmentEntity.stream().map(AppointmentMapper::toDomain).collect(Collectors.toList());
        
    }

    @Override
    public Appointment getById(UUID id) {
        Optional<AppointmentEntity> appointmentEntity = appointmentRepository.findById(id);
        if(appointmentEntity.isEmpty()){
            throw new EntityNotExistsException(Constants.appointmentNotFound);
        }
        return AppointmentMapper.toDomain(appointmentEntity.get());
    }

    @Override
    public Appointment create(Appointment apppointmentModel) {
        AppointmentEntity appointmentEntity = AppointmentMapper.toEntity(apppointmentModel);
        AppointmentEntity response = appointmentRepository.save(appointmentEntity);
        return AppointmentMapper.toDomain(response);
    }

    @Override
    public Appointment update(Appointment appointmentModel) {
        AppointmentEntity appointmentEntity = AppointmentMapper.toEntity(appointmentModel);
        AppointmentEntity response = appointmentRepository.save(appointmentEntity);
        return AppointmentMapper.toDomain(response);
    }

    @Override
    public void delete(UUID id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public List<Appointment> getOverLapping(Timestamp startDate, Timestamp endDate, String physicianIdentification) {
        List<AppointmentEntity> appointmentEntity = appointmentRepository.findOverlapping(startDate, endDate, physicianIdentification);
        return appointmentEntity.stream().map(AppointmentMapper::toDomain).collect(Collectors.toList());
    }
    
}
