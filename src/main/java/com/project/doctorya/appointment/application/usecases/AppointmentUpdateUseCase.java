package com.project.doctorya.appointment.application.usecases;

import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.doctorya.appointment.application.interfaces.IAppointmentUpdateUseCase;
import com.project.doctorya.appointment.domain.models.Appointment;
import com.project.doctorya.appointment.domain.repositories.IAppointmentRepository;
import com.project.doctorya.exceptions.EntityNotExistsException;
import com.project.doctorya.patient.domain.models.Patient;
import com.project.doctorya.patient.domain.repositories.IPatientRepository;
import com.project.doctorya.physician.domain.models.Physician;
import com.project.doctorya.physician.domain.repositories.IPhysicianRepository;
import com.project.doctorya.shared.Constants;

@Service
public class AppointmentUpdateUseCase implements IAppointmentUpdateUseCase {

    @Autowired
    private IAppointmentRepository appointmentRepository;
    @Autowired
    private IPatientRepository patientRepository;
    @Autowired
    private IPhysicianRepository physicianRepository;

    @Override
    public Appointment execute(Appointment appointmentModel, UUID id) {
        Appointment appointmentExists = appointmentRepository.getById(id);
        if(appointmentExists == null){
            throw new EntityNotExistsException(Constants.appointmentNotFound);
        }
        appointmentModel.setId(id);
        Patient patient = patientRepository.getByIdentification(appointmentModel.getPatient().getAuth().getIdentification());
        if(patient == null){
            throw new EntityNotExistsException(Constants.patientNotFound);
        }
        appointmentModel.setPatient(patient);
        Physician physicianModel = physicianRepository.getByIdentification(appointmentModel.getPhysician().getAuth().getIdentification());
        if(physicianModel == null){
            throw new EntityNotExistsException(Constants.physicianNotFound);
        }
        appointmentModel.setPhysician(physicianModel);
        Timestamp endDate = Timestamp.from(appointmentModel.getStartDate().toInstant().plus(appointmentModel.getDuration(), ChronoUnit.MINUTES));
        appointmentModel.setEndDate(endDate);
        return appointmentRepository.update(appointmentModel);
    }
    
}
