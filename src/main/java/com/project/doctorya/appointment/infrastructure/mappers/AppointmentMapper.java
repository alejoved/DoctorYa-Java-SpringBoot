package com.project.doctorya.appointment.infrastructure.mappers;

import com.project.doctorya.appointment.domain.models.AppointmentModel;
import com.project.doctorya.appointment.infrastructure.entities.Appointment;
import com.project.doctorya.auth.domain.models.AuthModel;
import com.project.doctorya.auth.infrastructure.entities.Auth;
import com.project.doctorya.patient.domain.models.PatientModel;
import com.project.doctorya.patient.infrastructure.entities.Patient;
import com.project.doctorya.physician.domain.models.PhysicianModel;
import com.project.doctorya.physician.infrastructure.entities.Physician;

public class AppointmentMapper {
    public static AppointmentModel toDomain(Appointment appointment){
        AppointmentModel appointmentModel = new AppointmentModel();
        appointmentModel.setId(appointment.getId());
        appointmentModel.setReason(appointment.getReason());
        appointmentModel.setDuration(appointment.getDuration());
        appointmentModel.setStartDate(appointment.getStartDate());
        appointmentModel.setEndDate(appointment.getEndDate());
        appointmentModel.setPatientModel(new PatientModel());
        appointmentModel.getPatientModel().setId(appointment.getPatient().getId());
        appointmentModel.getPatientModel().setName(appointment.getPatient().getName());
        appointmentModel.getPatientModel().setInsurance(appointment.getPatient().getInsurance());
        appointmentModel.getPatientModel().setAuthModel(new AuthModel());
        appointmentModel.getPatientModel().getAuthModel().setId(appointment.getPatient().getAuth().getId());
        appointmentModel.getPatientModel().getAuthModel().setIdentification(appointment.getPatient().getAuth().getIdentification());
        appointmentModel.setPhysicianModel(new PhysicianModel());
        appointmentModel.getPhysicianModel().setId(appointment.getPhysician().getId());
        appointmentModel.getPhysicianModel().setName(appointment.getPhysician().getName());
        appointmentModel.getPhysicianModel().setCode(appointment.getPhysician().getCode());
        appointmentModel.getPhysicianModel().setSpeciality(appointment.getPhysician().getSpeciality());
        appointmentModel.getPhysicianModel().setAuthModel(new AuthModel());
        appointmentModel.getPhysicianModel().getAuthModel().setId(appointment.getPhysician().getAuth().getId());
        appointmentModel.getPhysicianModel().getAuthModel().setIdentification(appointment.getPhysician().getAuth().getIdentification());
        return appointmentModel;

    }
    public static Appointment toEntity(AppointmentModel appointmentModel){
        Appointment appointment = new Appointment();
        appointment.setId(appointmentModel.getId());
        appointment.setReason(appointmentModel.getReason());
        appointment.setDuration(appointmentModel.getDuration());
        appointment.setStartDate(appointmentModel.getStartDate());
        appointment.setEndDate(appointmentModel.getEndDate());
        appointment.setPatient(new Patient());
        appointment.getPatient().setId(appointmentModel.getPatientModel().getId());
        appointment.getPatient().setName(appointmentModel.getPatientModel().getName());
        appointment.getPatient().setInsurance(appointmentModel.getPatientModel().getInsurance());
        appointment.getPatient().setAuth(new Auth());
        appointment.getPatient().getAuth().setId(appointmentModel.getPatientModel().getAuthModel().getId());
        appointment.getPatient().getAuth().setIdentification(appointmentModel.getPatientModel().getAuthModel().getIdentification());
        appointment.setPhysician(new Physician());
        appointment.getPhysician().setId(appointmentModel.getPhysicianModel().getId());
        appointment.getPhysician().setName(appointmentModel.getPhysicianModel().getName());
        appointment.getPhysician().setCode(appointmentModel.getPhysicianModel().getCode());
        appointment.getPhysician().setSpeciality(appointmentModel.getPhysicianModel().getSpeciality());
        appointment.getPhysician().setAuth(new Auth());
        appointment.getPhysician().getAuth().setId(appointmentModel.getPhysicianModel().getAuthModel().getId());
        appointment.getPhysician().getAuth().setIdentification(appointmentModel.getPhysicianModel().getAuthModel().getIdentification());
        return appointment;
    }
}
