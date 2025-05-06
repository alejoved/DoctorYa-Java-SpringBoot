package com.project.doctorya.appointment.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.doctorya.appointment.dto.AppointmentDTO;
import com.project.doctorya.appointment.dto.AppointmentResponseDTO;
import com.project.doctorya.appointment.entity.Appointment;
import com.project.doctorya.appointment.repository.AppointmentRepository;
import com.project.doctorya.common.Constants;
import com.project.doctorya.exceptions.ModelNotExistsException;

@Service
public class AppointmentService implements IAppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AppointmentResponseDTO> getAll() {
        List<Appointment> appointment = appointmentRepository.findAll();
        List<AppointmentResponseDTO> appointmentResponseDTO = modelMapper.map(appointment, new TypeToken<List<AppointmentResponseDTO>>() {}.getType());
        return appointmentResponseDTO;
    }

    @Override
    public AppointmentResponseDTO getById(UUID id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if(appointment.isEmpty()){
            throw new ModelNotExistsException(Constants.appointmentNotFound);
        }
        AppointmentResponseDTO appointmentResponseDTO = modelMapper.map(appointment, AppointmentResponseDTO.class);
        return appointmentResponseDTO;
    }

    @Override
    public AppointmentResponseDTO create(AppointmentDTO appointmentDTO) {
        Appointment patient = modelMapper.map(appointmentDTO, Appointment.class);
        Appointment response = appointmentRepository.save(patient);
        AppointmentResponseDTO appointmentResponseDTO = modelMapper.map(response, AppointmentResponseDTO.class);
        return appointmentResponseDTO;
    }

    @Override
    public AppointmentResponseDTO update(AppointmentDTO appointmentDTO, UUID id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if(appointment.isEmpty()){
            throw new ModelNotExistsException(Constants.appointmentNotFound);
        }
        modelMapper.map(appointmentDTO, appointment.get());
        Appointment response = appointmentRepository.save(appointment.get());
        AppointmentResponseDTO appointmentResponseDTO = modelMapper.map(response, AppointmentResponseDTO.class);
        return appointmentResponseDTO;
    }

    @Override
    public void delete(UUID id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if(appointment.isEmpty()){
            throw new ModelNotExistsException(Constants.appointmentNotFound);
        }
        appointmentRepository.delete(appointment.get());
    }

}
