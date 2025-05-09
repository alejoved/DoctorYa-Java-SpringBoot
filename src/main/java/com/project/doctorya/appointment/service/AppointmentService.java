package com.project.doctorya.appointment.service;

import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;
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
import com.project.doctorya.exceptions.EntityExistsException;
import com.project.doctorya.exceptions.EntityNotExistsException;
import com.project.doctorya.patient.entity.Patient;
import com.project.doctorya.patient.repository.PatientRepository;
import com.project.doctorya.physician.entity.Physician;
import com.project.doctorya.physician.repository.PhysicianRepository;

@Service
public class AppointmentService implements IAppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PhysicianRepository physicianRepository;

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
            throw new EntityNotExistsException(Constants.appointmentNotFound);
        }
        AppointmentResponseDTO appointmentResponseDTO = modelMapper.map(appointment, AppointmentResponseDTO.class);
        return appointmentResponseDTO;
    }

    @Override
    public AppointmentResponseDTO create(AppointmentDTO appointmentDTO) {
        Optional<Patient> patientExists = patientRepository.findByAuthIdentification(appointmentDTO.getPatientIdentification());
        if (patientExists.isEmpty()){
            throw new EntityNotExistsException(Constants.patientNotFound);
        }
        Optional<Physician> physicianExists = physicianRepository.findByAuthIdentification(appointmentDTO.getPhysicianIdentification());
        if (physicianExists.isEmpty()){
            throw new EntityNotExistsException(Constants.physicianNotFound);
        }
        Timestamp starDate = appointmentDTO.getStartDate();
        Timestamp endDate = Timestamp.from(appointmentDTO.getStartDate().toInstant().plus(appointmentDTO.getDuration(), ChronoUnit.MINUTES));
        List<Appointment> appointmentExists = appointmentRepository.findOverlapping(starDate, endDate);
        if(appointmentExists.size() > 0){
            throw new EntityExistsException(Constants.appointmentExists);
        }
        Appointment appointment = modelMapper.map(appointmentDTO, Appointment.class);
        appointment.setPatient(patientExists.get());
        appointment.setPhysician(physicianExists.get());
        appointment.setEndDate(endDate);
        Appointment response = appointmentRepository.save(appointment);
        AppointmentResponseDTO appointmentResponseDTO = modelMapper.map(response, AppointmentResponseDTO.class);
        return appointmentResponseDTO;
    }

    @Override
    public AppointmentResponseDTO update(AppointmentDTO appointmentDTO, UUID id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if(appointment.isEmpty()){
            throw new EntityNotExistsException(Constants.appointmentNotFound);
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
            throw new EntityNotExistsException(Constants.appointmentNotFound);
        }
        appointmentRepository.delete(appointment.get());
    }

}
