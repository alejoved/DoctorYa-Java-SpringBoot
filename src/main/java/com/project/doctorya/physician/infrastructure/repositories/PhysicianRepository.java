package com.project.doctorya.physician.infrastructure.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.doctorya.exceptions.EntityNotExistsException;
import com.project.doctorya.physician.domain.models.Physician;
import com.project.doctorya.physician.domain.repositories.IPhysicianRepository;
import com.project.doctorya.physician.infrastructure.entities.PhysicianEntity;
import com.project.doctorya.physician.infrastructure.mappers.PhysicianMapper;
import com.project.doctorya.shared.Constants;

@Repository
@Primary
public class PhysicianRepository implements IPhysicianRepository {
    @Autowired
    private IPhysicianJpaRepository physicianRepository;

    @Override
    public List<Physician> get() {
        List<PhysicianEntity> physicians = physicianRepository.findAll();
        return physicians.stream().map(PhysicianMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Physician getById(UUID id) {
        Optional<PhysicianEntity> physician = physicianRepository.findById(id);
        if(physician.isEmpty()){
            return null;
        }
        return PhysicianMapper.toDomain(physician.get());

    }

    @Override
    public Physician getByIdentification(String identification) {
        Optional<PhysicianEntity> physicianEntity = physicianRepository.findByAuthEntityIdentification(identification);
        if(physicianEntity.isEmpty()){
            return null;
        }
        return PhysicianMapper.toDomain(physicianEntity.get());
    }

    @Override
    public Physician create(Physician physician) {
        PhysicianEntity physicianEntity = PhysicianMapper.toEntity(physician);
        PhysicianEntity response = physicianRepository.save(physicianEntity);
        return PhysicianMapper.toDomain(response);
    }

    @Override
    public Physician update(Physician physician) {
        PhysicianEntity physicianEntity = PhysicianMapper.toEntity(physician);
        PhysicianEntity response = physicianRepository.save(physicianEntity);
        return PhysicianMapper.toDomain(response);
    }

    @Override
    public void delete(UUID id) {
        Optional<PhysicianEntity> physician = physicianRepository.findById(id);
        if(physician.isEmpty()){
            throw new EntityNotExistsException(Constants.physicianNotFound);
        }
        physicianRepository.delete(physician.get());
    }
}
