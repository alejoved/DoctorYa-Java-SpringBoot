package com.project.doctorya.appointment.application.usecases;

import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.doctorya.appointment.application.interfaces.IAppointmentUpdateUseCase;
import com.project.doctorya.appointment.domain.models.AppointmentModel;
import com.project.doctorya.appointment.domain.repositories.IAppointmentRepository;
import com.project.doctorya.exceptions.EntityNotExistsException;
import com.project.doctorya.patient.domain.models.PatientModel;
import com.project.doctorya.patient.domain.repositories.IPatientRepository;
import com.project.doctorya.physician.domain.models.PhysicianModel;
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
    public AppointmentModel execute(AppointmentModel appointmentModel, UUID id) {
        AppointmentModel appointmentExists = appointmentRepository.getById(id);
        if(appointmentExists == null){
            throw new EntityNotExistsException(Constants.appointmentNotFound);
        }
        appointmentModel.setId(id);
        PatientModel patientModel = patientRepository.getByIdentification(appointmentModel.getPatientModel().getAuthModel().getIdentification());
        if(patientModel == null){
            throw new EntityNotExistsException(Constants.patientNotFound);
        }
        appointmentModel.setPatientModel(patientModel);
        PhysicianModel physicianModel = physicianRepository.getByIdentification(appointmentModel.getPhysicianModel().getAuthModel().getIdentification());
        if(physicianModel == null){
            throw new EntityNotExistsException(Constants.physicianNotFound);
        }
        appointmentModel.setPhysicianModel(physicianModel);
        Timestamp endDate = Timestamp.from(appointmentModel.getStartDate().toInstant().plus(appointmentModel.getDuration(), ChronoUnit.MINUTES));
        appointmentModel.setEndDate(endDate);
        return appointmentRepository.update(appointmentModel);
    }
    
}
