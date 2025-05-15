package com.project.doctorya.patient.service;

import com.project.doctorya.common.ICRUD;
import com.project.doctorya.patient.dto.PatientDTO;
import com.project.doctorya.patient.dto.PatientResponseDTO;


public interface IPatientService extends ICRUD<PatientDTO, PatientResponseDTO> {
    public PatientResponseDTO getByIdentification(String identification);

}
