package com.project.doctorya.physician.rest.mapper;

import com.project.doctorya.auth.domain.models.Auth;
import com.project.doctorya.physician.domain.models.Physician;
import com.project.doctorya.physician.rest.dto.PhysicianDto;
import com.project.doctorya.physician.rest.dto.PhysicianResponseDto;

public class PhysicianRestMapper {
    public static Physician toDomain(PhysicianDto physicianDTO){
        Physician physician = new Physician();
        physician.setName(physicianDTO.getName());
        physician.setCode(physicianDTO.getCode());
        physician.setSpeciality(physicianDTO.getSpeciality());
        physician.setAuth(new Auth());
        physician.getAuth().setIdentification(physicianDTO.getIdentification());
        physician.getAuth().setPassword(physicianDTO.getPassword());
        return physician;

    }
    public static PhysicianResponseDto toDto(Physician physician){
        PhysicianResponseDto physicianResponseDto = new PhysicianResponseDto();
        physicianResponseDto.setName(physician.getName());
        physicianResponseDto.setCode(physician.getCode());
        physicianResponseDto.setSpeciality(physician.getSpeciality());
        physicianResponseDto.setIdentification(physician.getAuth().getIdentification());
        return physicianResponseDto;
    }    
}
