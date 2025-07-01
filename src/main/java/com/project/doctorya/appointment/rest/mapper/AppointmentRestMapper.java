package com.project.doctorya.appointment.rest.mapper;

import com.project.doctorya.appointment.domain.models.AppointmentModel;
import com.project.doctorya.appointment.rest.dto.AppointmentDTO;
import com.project.doctorya.appointment.rest.dto.AppointmentResponseDTO;
import com.project.doctorya.auth.domain.models.AuthModel;
import com.project.doctorya.patient.domain.models.PatientModel;
import com.project.doctorya.patient.rest.dto.PatientResponseDTO;
import com.project.doctorya.physician.domain.models.PhysicianModel;
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
        appointmentResponseDTO.setId(appointmentModel.getId());
        appointmentResponseDTO.setReason(appointmentModel.getReason());
        appointmentResponseDTO.setStartDate(appointmentModel.getStartDate());
        appointmentResponseDTO.setEndDate(appointmentModel.getEndDate());
        appointmentResponseDTO.setPatient(new PatientResponseDTO());
        appointmentResponseDTO.getPatient().setIdentification(appointmentModel.getPatientModel().getAuthModel().getIdentification());
        appointmentResponseDTO.getPatient().setName(appointmentModel.getPatientModel().getName());
        appointmentResponseDTO.getPatient().setInsurance(appointmentModel.getPatientModel().getInsurance());
        appointmentResponseDTO.setPhysician(new PhysicianResponseDTO());
        appointmentResponseDTO.getPhysician().setIdentification(appointmentModel.getPhysicianModel().getAuthModel().getIdentification());
        appointmentResponseDTO.getPhysician().setName(appointmentModel.getPhysicianModel().getName());
        appointmentResponseDTO.getPhysician().setCode(appointmentModel.getPhysicianModel().getCode());
        appointmentResponseDTO.getPhysician().setSpeciality(appointmentModel.getPhysicianModel().getSpeciality());
        return appointmentResponseDTO;
    }    
}
