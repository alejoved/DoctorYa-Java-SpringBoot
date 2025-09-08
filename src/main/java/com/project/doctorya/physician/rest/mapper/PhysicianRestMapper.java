package com.project.doctorya.physician.rest.mapper;

import com.project.doctorya.auth.domain.models.Auth;
import com.project.doctorya.physician.domain.models.Physician;
import com.project.doctorya.physician.rest.dto.PhysicianDTO;
import com.project.doctorya.physician.rest.dto.PhysicianResponseDTO;

public class PhysicianRestMapper {
    public static Physician toDomain(PhysicianDTO physicianDTO){
        Physician physician = new Physician();
        physician.setName(physicianDTO.getName());
        physician.setCode(physicianDTO.getCode());
        physician.setSpeciality(physicianDTO.getSpeciality());
        physician.setAuth(new Auth());
        physician.getAuth().setIdentification(physicianDTO.getIdentification());
        physician.getAuth().setPassword(physicianDTO.getPassword());
        return physician;

    }
    public static PhysicianResponseDTO toDTO(Physician physician){
        PhysicianResponseDTO physicianResponseDTO = new PhysicianResponseDTO();
        physicianResponseDTO.setName(physician.getName());
        physicianResponseDTO.setCode(physician.getCode());
        physicianResponseDTO.setSpeciality(physician.getSpeciality());
        physicianResponseDTO.setIdentification(physician.getAuth().getIdentification());
        return physicianResponseDTO;
    }    
}
