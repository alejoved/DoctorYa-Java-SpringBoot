package com.project.doctorya.physician.infrastructure.mappers;

import com.project.doctorya.auth.domain.models.Auth;
import com.project.doctorya.auth.infrastructure.entities.AuthEntity;
import com.project.doctorya.physician.domain.models.Physician;
import com.project.doctorya.physician.infrastructure.entities.PhysicianEntity;

public class PhysicianMapper{
    public static Physician toDomain(PhysicianEntity physicianEntity){
        Physician physicianModel = new Physician();
        physicianModel.setId(physicianEntity.getId());
        physicianModel.setName(physicianEntity.getName());
        physicianModel.setCode(physicianEntity.getCode());
        physicianModel.setSpeciality(physicianEntity.getSpeciality());
        physicianModel.setAuth(new Auth());
        physicianModel.getAuth().setId(physicianEntity.getAuthEntity().getId());
        physicianModel.getAuth().setIdentification(physicianEntity.getAuthEntity().getIdentification());
        physicianModel.getAuth().setPassword(physicianEntity.getAuthEntity().getPassword());
        physicianModel.getAuth().setRole(physicianEntity.getAuthEntity().getRole());
        return physicianModel;

    }
    public static PhysicianEntity toEntity(Physician physician){
        PhysicianEntity physicianEntity = new PhysicianEntity();
        physicianEntity.setId(physician.getId());
        physicianEntity.setName(physician.getName());
        physicianEntity.setCode(physician.getCode());
        physicianEntity.setSpeciality(physician.getSpeciality());
        physicianEntity.setAuthEntity(new AuthEntity());
        physicianEntity.getAuthEntity().setId(physician.getAuth().getId());
        physicianEntity.getAuthEntity().setIdentification(physician.getAuth().getIdentification());
        physicianEntity.getAuthEntity().setPassword(physician.getAuth().getPassword());
        physicianEntity.getAuthEntity().setRole(physician.getAuth().getRole());
        return physicianEntity;
    }
}