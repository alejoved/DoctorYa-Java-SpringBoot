package com.project.doctorya.appointment.rest.mapper;

import com.project.doctorya.appointment.domain.models.Appointment;
import com.project.doctorya.appointment.rest.dto.AppointmentDto;
import com.project.doctorya.appointment.rest.dto.AppointmentResponseDto;
import com.project.doctorya.auth.domain.models.Auth;
import com.project.doctorya.patient.domain.models.Patient;
import com.project.doctorya.patient.rest.dto.PatientResponseDto;
import com.project.doctorya.physician.domain.models.Physician;
import com.project.doctorya.physician.rest.dto.PhysicianResponseDto;

public class AppointmentRestMapper {
    public static Appointment toDomain(AppointmentDto appointmentDTO){
        Appointment appointmentModel = new Appointment();
        appointmentModel.setDuration(appointmentDTO.getDuration());
        appointmentModel.setStartDate(appointmentDTO.getStartDate());
        appointmentModel.setReason(appointmentDTO.getReason());
        appointmentModel.setPatient(new Patient());
        appointmentModel.getPatient().setAuth(new Auth());
        appointmentModel.getPatient().getAuth().setIdentification(appointmentDTO.getPatientIdentification());
        appointmentModel.setPhysician(new Physician());
        appointmentModel.getPhysician().setAuth(new Auth());
        appointmentModel.getPhysician().getAuth().setIdentification(appointmentDTO.getPhysicianIdentification());
        return appointmentModel;

    }
    public static AppointmentResponseDto toDTO(Appointment appointmentModel){
        AppointmentResponseDto appointmentResponseDTO = new AppointmentResponseDto();
        appointmentResponseDTO.setId(appointmentModel.getId());
        appointmentResponseDTO.setReason(appointmentModel.getReason());
        appointmentResponseDTO.setStartDate(appointmentModel.getStartDate());
        appointmentResponseDTO.setEndDate(appointmentModel.getEndDate());
        appointmentResponseDTO.setPatient(new PatientResponseDto());
        appointmentResponseDTO.getPatient().setIdentification(appointmentModel.getPatient().getAuth().getIdentification());
        appointmentResponseDTO.getPatient().setName(appointmentModel.getPatient().getName());
        appointmentResponseDTO.getPatient().setInsurance(appointmentModel.getPatient().getInsurance());
        appointmentResponseDTO.setPhysician(new PhysicianResponseDto());
        appointmentResponseDTO.getPhysician().setIdentification(appointmentModel.getPhysician().getAuth().getIdentification());
        appointmentResponseDTO.getPhysician().setName(appointmentModel.getPhysician().getName());
        appointmentResponseDTO.getPhysician().setCode(appointmentModel.getPhysician().getCode());
        appointmentResponseDTO.getPhysician().setSpeciality(appointmentModel.getPhysician().getSpeciality());
        return appointmentResponseDTO;
    }    
}
