package com.project.doctorya.physician.infrastructure.mapper;

import com.project.doctorya.auth.domain.model.AuthModel;
import com.project.doctorya.auth.infrastructure.entity.Auth;
import com.project.doctorya.physician.domain.model.PhysicianModel;
import com.project.doctorya.physician.infrastructure.entity.Physician;

public class PhysicianMapper{
    public static PhysicianModel toDomain(Physician physician){
        PhysicianModel physicianModel = new PhysicianModel();
        physicianModel.setId(physician.getId());
        physicianModel.setName(physician.getName());
        physicianModel.setCode(physician.getCode());
        physicianModel.setSpeciality(physician.getSpeciality());
        physicianModel.setAuthModel(new AuthModel());
        physicianModel.getAuthModel().setId(physician.getAuth().getId());
        physicianModel.getAuthModel().setIdentification(physician.getAuth().getIdentification());
        physicianModel.getAuthModel().setPassword(physician.getAuth().getPassword());
        physicianModel.getAuthModel().setRole(physician.getAuth().getRole());
        return physicianModel;

    }
    public static Physician toEntity(PhysicianModel physicianModel){
        Physician physician = new Physician();
        physician.setId(physicianModel.getId());
        physician.setName(physicianModel.getName());
        physician.setCode(physicianModel.getCode());
        physician.setSpeciality(physicianModel.getSpeciality());
        physician.setAuth(new Auth());
        physician.getAuth().setId(physicianModel.getAuthModel().getId());
        physician.getAuth().setIdentification(physicianModel.getAuthModel().getIdentification());
        physician.getAuth().setPassword(physicianModel.getAuthModel().getPassword());
        physician.getAuth().setRole(physicianModel.getAuthModel().getRole());
        return physician;
    }
}