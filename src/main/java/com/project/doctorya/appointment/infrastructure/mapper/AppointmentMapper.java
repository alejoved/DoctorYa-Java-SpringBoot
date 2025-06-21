package com.project.doctorya.appointment.infrastructure.mapper;

import com.project.doctorya.appointment.domain.model.AppointmentModel;
import com.project.doctorya.appointment.infrastructure.entity.Appointment;
import com.project.doctorya.auth.domain.model.AuthModel;
import com.project.doctorya.patient.domain.model.PatientModel;
import com.project.doctorya.patient.infrastructure.entity.Patient;
import com.project.doctorya.physician.domain.model.PhysicianModel;
import com.project.doctorya.physician.infrastructure.entity.Physician;

public class AppointmentMapper {
    public static AppointmentModel toDomain(Appointment appointment){
        AppointmentModel appointmentModel = new AppointmentModel();
        appointmentModel.setReason(appointment.getReason());
        appointmentModel.setDuration(appointment.getDuration());
        appointmentModel.setStartDate(appointment.getStartDate());
        appointmentModel.setEndDate(appointment.getEndDate());
        appointmentModel.setPhysicianModel(new PhysicianModel());
        appointmentModel.getPhysicianModel().setId(appointment.getPhysician().getId());
        appointmentModel.getPhysicianModel().setName(appointment.getPhysician().getName());
        appointmentModel.getPhysicianModel().setSpeciality(appointmentModel.getPhysicianModel().getSpeciality());
        appointmentModel.getPhysicianModel().setAuthModel(new AuthModel());
        appointmentModel.getPhysicianModel().getAuthModel().setId(appointment.getPhysician().getAuth().getId());
        appointmentModel.getPhysicianModel().getAuthModel().setIdentification(appointment.getPhysician().getAuth().getIdentification());
        appointmentModel.setPatientModel(new PatientModel());
        appointmentModel.getPatientModel().setId(appointment.getPatient().getId());
        appointmentModel.getPatientModel().setName(appointment.getPatient().getName());
        appointmentModel.getPatientModel().setInsurance(appointment.getPatient().getInsurance());
        appointmentModel.getPatientModel().setAuthModel(new AuthModel());
        appointmentModel.getPatientModel().getAuthModel().setId(appointment.getPatient().getAuth().getId());
        appointmentModel.getPatientModel().getAuthModel().setIdentification(appointment.getPatient().getAuth().getIdentification());
        return appointmentModel;

    }
    public static Appointment toEntity(AppointmentModel appointmentModel){
        Appointment appointment = new Appointment();
        appointment.setReason(appointmentModel.getReason());
        appointment.setDuration(appointmentModel.getDuration());
        appointment.setStartDate(appointmentModel.getStartDate());
        appointment.setEndDate(appointmentModel.getEndDate());
        appointment.setPhysician(new Physician());
        appointment.getPhysician().setId(appointmentModel.getPhysicianModel().getId());
        appointment.setPatient(new Patient());
        appointment.getPatient().setId(appointmentModel.getPatientModel().getId());
        return appointment;
    }
}
