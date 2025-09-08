package com.project.doctorya.appointment.infrastructure.mappers;

import com.project.doctorya.appointment.domain.models.Appointment;
import com.project.doctorya.appointment.infrastructure.entities.AppointmentEntity;
import com.project.doctorya.auth.domain.models.Auth;
import com.project.doctorya.auth.infrastructure.entities.AuthEntity;
import com.project.doctorya.patient.domain.models.Patient;
import com.project.doctorya.patient.infrastructure.entities.PatientEntity;
import com.project.doctorya.physician.domain.models.Physician;
import com.project.doctorya.physician.infrastructure.entities.PhysicianEntity;

public class AppointmentMapper {
    public static Appointment toDomain(AppointmentEntity appointmentEntity){
        Appointment appointment = new Appointment();
        appointment.setId(appointmentEntity.getId());
        appointment.setReason(appointmentEntity.getReason());
        appointment.setDuration(appointmentEntity.getDuration());
        appointment.setStartDate(appointmentEntity.getStartDate());
        appointment.setEndDate(appointmentEntity.getEndDate());
        appointment.setPatient(new Patient());
        appointment.getPatient().setId(appointmentEntity.getPatientEntity().getId());
        appointment.getPatient().setName(appointmentEntity.getPatientEntity().getName());
        appointment.getPatient().setInsurance(appointmentEntity.getPatientEntity().getInsurance());
        appointment.getPatient().setAuth(new Auth());
        appointment.getPatient().getAuth().setId(appointmentEntity.getPatientEntity().getAuthEntity().getId());
        appointment.getPatient().getAuth().setIdentification(appointmentEntity.getPatientEntity().getAuthEntity().getIdentification());
        appointment.setPhysician(new Physician());
        appointment.getPhysician().setId(appointmentEntity.getPhysicianEntity().getId());
        appointment.getPhysician().setName(appointmentEntity.getPhysicianEntity().getName());
        appointment.getPhysician().setCode(appointmentEntity.getPhysicianEntity().getCode());
        appointment.getPhysician().setSpeciality(appointmentEntity.getPhysicianEntity().getSpeciality());
        appointment.getPhysician().setAuth(new Auth());
        appointment.getPhysician().getAuth().setId(appointmentEntity.getPhysicianEntity().getAuthEntity().getId());
        appointment.getPhysician().getAuth().setIdentification(appointmentEntity.getPhysicianEntity().getAuthEntity().getIdentification());
        return appointment;

    }
    public static AppointmentEntity toEntity(Appointment appointment){
        AppointmentEntity appointmentEntity = new AppointmentEntity();
        appointmentEntity.setId(appointment.getId());
        appointmentEntity.setReason(appointment.getReason());
        appointmentEntity.setDuration(appointment.getDuration());
        appointmentEntity.setStartDate(appointment.getStartDate());
        appointmentEntity.setEndDate(appointment.getEndDate());
        appointmentEntity.setPatientEntity(new PatientEntity());
        appointmentEntity.getPatientEntity().setId(appointment.getPatient().getId());
        appointmentEntity.getPatientEntity().setName(appointment.getPatient().getName());
        appointmentEntity.getPatientEntity().setInsurance(appointment.getPatient().getInsurance());
        appointmentEntity.getPatientEntity().setAuthEntity(new AuthEntity());
        appointmentEntity.getPatientEntity().getAuthEntity().setId(appointment.getPatient().getAuth().getId());
        appointmentEntity.getPatientEntity().getAuthEntity().setIdentification(appointment.getPatient().getAuth().getIdentification());
        appointmentEntity.setPhysicianEntity(new PhysicianEntity());
        appointmentEntity.getPhysicianEntity().setId(appointment.getPhysician().getId());
        appointmentEntity.getPhysicianEntity().setName(appointment.getPhysician().getName());
        appointmentEntity.getPhysicianEntity().setCode(appointment.getPhysician().getCode());
        appointmentEntity.getPhysicianEntity().setSpeciality(appointment.getPhysician().getSpeciality());
        appointmentEntity.getPhysicianEntity().setAuthEntity(new AuthEntity());
        appointmentEntity.getPhysicianEntity().getAuthEntity().setId(appointment.getPhysician().getAuth().getId());
        appointmentEntity.getPhysicianEntity().getAuthEntity().setIdentification(appointment.getPhysician().getAuth().getIdentification());
        return appointmentEntity;
    }
}
