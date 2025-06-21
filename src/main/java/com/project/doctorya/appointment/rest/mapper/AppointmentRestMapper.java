package com.project.doctorya.appointment.rest.mapper;

import com.project.doctorya.appointment.domain.model.AppointmentModel;
import com.project.doctorya.appointment.rest.dto.AppointmentDTO;
import com.project.doctorya.appointment.rest.dto.AppointmentResponseDTO;
import com.project.doctorya.auth.domain.model.AuthModel;
import com.project.doctorya.patient.domain.model.PatientModel;
import com.project.doctorya.patient.rest.dto.PatientResponseDTO;
import com.project.doctorya.physician.domain.model.PhysicianModel;
import com.project.doctorya.physician.rest.dto.PhysicianResponseDTO;

public class AppointmentRestMapper {
    public static AppointmentModel toDomain(AppointmentDTO appointmentDTO){
        AppointmentModel appointmentModel = new AppointmentModel();
        appointmentModel.setDuration(appointmentDTO.getDuration());
        appointmentModel.setStartDate(appointmentDTO.getStartDate());
        appointmentModel.setReason(appointmentDTO.getReason());
        appointmentModel.setPatientModel(new PatientModel());
        appointmentModel.getPatientModel().setAuthModel(new AuthModel());
        appointmentModel.getPatientModel().getAuthModel().setIdentification(appointmentDTO.getPatientIdentification());
        appointmentModel.setPhysicianModel(new PhysicianModel());
        appointmentModel.getPhysicianModel().setAuthModel(new AuthModel());
        appointmentModel.getPhysicianModel().getAuthModel().setIdentification(appointmentDTO.getPhysicianIdentification());
        return appointmentModel;

    }
    public static AppointmentResponseDTO toDTO(AppointmentModel appointmentModel){
        AppointmentResponseDTO appointmentResponseDTO = new AppointmentResponseDTO();
        appointmentResponseDTO.setReason(appointmentModel.getReason());
        appointmentResponseDTO.setStartDate(appointmentModel.getStartDate());
        appointmentResponseDTO.setEndDate(appointmentModel.getEndDate());
        appointmentResponseDTO.setPatient(new PatientResponseDTO());
        appointmentResponseDTO.getPatient().setName(appointmentModel.getPatientModel().getName());
        appointmentResponseDTO.setPhysician(new PhysicianResponseDTO());
        appointmentResponseDTO.getPhysician().setName(appointmentModel.getPhysicianModel().getName());
        return appointmentResponseDTO;
    }    
}
