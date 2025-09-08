package com.project.doctorya.appointment.application.usecases;

import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.doctorya.appointment.application.interfaces.IAppointmentCreateUseCase;
import com.project.doctorya.appointment.domain.models.Appointment;
import com.project.doctorya.appointment.domain.repositories.IAppointmentRepository;
import com.project.doctorya.exceptions.EntityExistsException;
import com.project.doctorya.exceptions.EntityNotExistsException;
import com.project.doctorya.patient.domain.models.Patient;
import com.project.doctorya.patient.domain.repositories.IPatientRepository;
import com.project.doctorya.physician.domain.models.Physician;
import com.project.doctorya.physician.domain.repositories.IPhysicianRepository;
import com.project.doctorya.shared.Constants;

@Service
public class AppointmentCreateUseCase implements IAppointmentCreateUseCase {

    @Autowired
    private IAppointmentRepository appointmentRepository;

    @Autowired
    private IPatientRepository patientRepository;

    @Autowired
    private IPhysicianRepository physicianRepository;

    @Override
    public Appointment execute(Appointment appointment) {
        Patient patientFound = patientRepository.getByIdentification(appointment.getPatient().getAuth().getIdentification());
        if (patientFound == null){
            throw new EntityNotExistsException(Constants.patientNotFound);
        }
        Physician physicianFound = physicianRepository.getByIdentification(appointment.getPhysician().getAuth().getIdentification());
        if (physicianFound == null){
            throw new EntityNotExistsException(Constants.physicianNotFound);
        }
        Timestamp starDate = appointment.getStartDate();
        Timestamp endDate = Timestamp.from(appointment.getStartDate().toInstant().plus(appointment.getDuration(), ChronoUnit.MINUTES));
        List<Appointment> appointmentFound = appointmentRepository.getOverLapping(starDate, endDate, appointment.getPhysician().getAuth().getIdentification());
        if(appointmentFound.size() > 0){
            throw new EntityExistsException(Constants.appointmentExists);
        }
        appointment.setEndDate(endDate);
        appointment.setPatient(patientFound);
        appointment.setPhysician(physicianFound);
        return appointmentRepository.create(appointment);
    }
    
}
