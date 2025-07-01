package com.project.doctorya.physician.rest.mapper;

import com.project.doctorya.auth.domain.models.AuthModel;
import com.project.doctorya.physician.domain.models.PhysicianModel;
import com.project.doctorya.physician.rest.dto.PhysicianDTO;
import com.project.doctorya.physician.rest.dto.PhysicianResponseDTO;

public class PhysicianRestMapper {
    public static PhysicianModel toDomain(PhysicianDTO physicianDTO){
        PhysicianModel physicianModel = new PhysicianModel();
        physicianModel.setName(physicianDTO.getName());
        physicianModel.setCode(physicianDTO.getCode());
        physicianModel.setSpeciality(physicianDTO.getSpeciality());
        physicianModel.setAuthModel(new AuthModel());
        physicianModel.getAuthModel().setIdentification(physicianDTO.getIdentification());
        physicianModel.getAuthModel().setPassword(physicianDTO.getPassword());
        return physicianModel;

    }
    public static PhysicianResponseDTO toDTO(PhysicianModel physicianModel){
        PhysicianResponseDTO physicianResponseDTO = new PhysicianResponseDTO();
        physicianResponseDTO.setName(physicianModel.getName());
        physicianResponseDTO.setCode(physicianModel.getCode());
        physicianResponseDTO.setSpeciality(physicianModel.getSpeciality());
        physicianResponseDTO.setIdentification(physicianModel.getAuthModel().getIdentification());
        return physicianResponseDTO;
    }    
}
