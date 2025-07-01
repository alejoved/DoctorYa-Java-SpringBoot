package com.project.doctorya.appointment.application.usecases;

import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.doctorya.appointment.application.interfaces.IAppointmentCreateUseCase;
import com.project.doctorya.appointment.domain.models.AppointmentModel;
import com.project.doctorya.appointment.domain.repositories.IAppointmentRepository;
import com.project.doctorya.exceptions.EntityExistsException;
import com.project.doctorya.exceptions.EntityNotExistsException;
import com.project.doctorya.patient.domain.models.PatientModel;
import com.project.doctorya.patient.domain.repositories.IPatientRepository;
import com.project.doctorya.physician.domain.models.PhysicianModel;
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
    public AppointmentModel execute(AppointmentModel appointmentModel) {
        PatientModel patientFound = patientRepository.getByIdentification(appointmentModel.getPatientModel().getAuthModel().getIdentification());
        if (patientFound == null){
            throw new EntityNotExistsException(Constants.patientNotFound);
        }
        PhysicianModel physicianFound = physicianRepository.getByIdentification(appointmentModel.getPhysicianModel().getAuthModel().getIdentification());
        if (physicianFound == null){
            throw new EntityNotExistsException(Constants.physicianNotFound);
        }
        Timestamp starDate = appointmentModel.getStartDate();
        Timestamp endDate = Timestamp.from(appointmentModel.getStartDate().toInstant().plus(appointmentModel.getDuration(), ChronoUnit.MINUTES));
        List<AppointmentModel> appointmentFound = appointmentRepository.getOverLapping(starDate, endDate, appointmentModel.getPhysicianModel().getAuthModel().getIdentification());
        if(appointmentFound.size() > 0){
            throw new EntityExistsException(Constants.appointmentExists);
        }
        appointmentModel.setEndDate(endDate);
        appointmentModel.setPatientModel(patientFound);
        appointmentModel.setPhysicianModel(physicianFound);
        return appointmentRepository.create(appointmentModel);
    }
    
}
