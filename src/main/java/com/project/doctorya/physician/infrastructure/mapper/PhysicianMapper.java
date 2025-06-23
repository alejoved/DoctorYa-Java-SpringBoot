package com.project.doctorya.physician.infrastructure.mapper;

import com.project.doctorya.auth.domain.model.AuthModel;
import com.project.doctorya.auth.infrastructure.entity.Auth;
import com.project.doctorya.physician.domain.model.PhysicianModel;
import com.project.doctorya.physician.infrastructure.entity.Physician;

public class PhysicianMapper{
    public static PhysicianModel toDomain(Physician physician){
        PhysicianModel physicianModel = new PhysicianModel();
        physicianModel.setName(physician.getName());
        physicianModel.setCode(physician.getCode());
        physicianModel.setSpeciality(physician.getSpeciality());
        physicianModel.setAuthModel(new AuthModel());
        physicianModel.getAuthModel().setIdentification(physician.getAuth().getIdentification());
        physicianModel.getAuthModel().setPassword(physician.getAuth().getPassword());
        return physicianModel;

    }
    public static Physician toEntity(PhysicianModel physicianModel){
        Physician physician = new Physician();
        physician.setName(physicianModel.getName());
        physician.setCode(physicianModel.getCode());
        physician.setSpeciality(physicianModel.getSpeciality());
        physician.setAuth(new Auth());
        physician.getAuth().setIdentification(physicianModel.getAuthModel().getIdentification());
        physician.getAuth().setPassword(physicianModel.getAuthModel().getPassword());
        return physician;
    }
}