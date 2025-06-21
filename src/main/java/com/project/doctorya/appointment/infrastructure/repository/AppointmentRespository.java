package com.project.doctorya.appointment.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.doctorya.appointment.domain.model.AppointmentModel;
import com.project.doctorya.appointment.domain.repository.IAppointmentRepository;
import com.project.doctorya.appointment.infrastructure.entity.Appointment;
import com.project.doctorya.appointment.infrastructure.mapper.AppointmentMapper;
import com.project.doctorya.exceptions.EntityNotExistsException;
import com.project.doctorya.shared.Constants;

public class AppointmentRespository implements IAppointmentRepository{

    @Autowired
    private IAppointmentJpaRepository appointmentRepository;

    @Override
    public List<AppointmentModel> get() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream().map(AppointmentMapper::toDomain).collect(Collectors.toList());
        
    }

    @Override
    public AppointmentModel getById(UUID id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if(appointment.isEmpty()){
            throw new EntityNotExistsException(Constants.appointmentNotFound);
        }
        return AppointmentMapper.toDomain(appointment.get());
    }

    @Override
    public AppointmentModel create(AppointmentModel apppointmentModel) {
        Appointment appointment = AppointmentMapper.toEntity(apppointmentModel);
        Appointment response = appointmentRepository.save(appointment);
        return AppointmentMapper.toDomain(response);
    }

    @Override
    public AppointmentModel update(AppointmentModel appointmentModel) {
        Appointment appointment = AppointmentMapper.toEntity(appointmentModel);
        Appointment response = appointmentRepository.save(appointment);
        return AppointmentMapper.toDomain(response);
    }

    @Override
    public void delete(UUID id) {
        appointmentRepository.deleteById(id);
    }
    
}
