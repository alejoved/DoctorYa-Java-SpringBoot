package com.project.doctorya.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.doctorya.dtos.PatientDTO;
import com.project.doctorya.services.interf.IPatientService;

@Service
public class PatientService implements IPatientService {

    @Override
    public List<PatientDTO> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public PatientDTO getById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public PatientDTO create(PatientDTO obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public PatientDTO update(PatientDTO obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
