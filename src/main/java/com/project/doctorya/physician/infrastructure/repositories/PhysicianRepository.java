package com.project.doctorya.physician.infrastructure.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.doctorya.exceptions.EntityNotExistsException;
import com.project.doctorya.physician.domain.models.PhysicianModel;
import com.project.doctorya.physician.domain.repositories.IPhysicianRepository;
import com.project.doctorya.physician.infrastructure.entities.Physician;
import com.project.doctorya.physician.infrastructure.mappers.PhysicianMapper;
import com.project.doctorya.shared.Constants;

@Repository
@Primary
public class PhysicianRepository implements IPhysicianRepository {
    @Autowired
    private IPhysicianJpaRepository physicianRepository;

    @Override
    public List<PhysicianModel> get() {
        List<Physician> physicians = physicianRepository.findAll();
        return physicians.stream().map(PhysicianMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public PhysicianModel getById(UUID id) {
        Optional<Physician> physician = physicianRepository.findById(id);
        if(physician.isEmpty()){
            return null;
        }
        return PhysicianMapper.toDomain(physician.get());

    }

    @Override
    public PhysicianModel getByIdentification(String identification) {
        Optional<Physician> physician = physicianRepository.findByAuthIdentification(identification);
        if(physician.isEmpty()){
            return null;
        }
        return PhysicianMapper.toDomain(physician.get());
    }

    @Override
    public PhysicianModel create(PhysicianModel physicianModel) {
        Physician physician = PhysicianMapper.toEntity(physicianModel);
        Physician response = physicianRepository.save(physician);
        return PhysicianMapper.toDomain(response);
    }

    @Override
    public PhysicianModel update(PhysicianModel physicianModel) {
        Physician physician = PhysicianMapper.toEntity(physicianModel);
        Physician response = physicianRepository.save(physician);
        return PhysicianMapper.toDomain(response);
    }

    @Override
    public void delete(UUID id) {
        Optional<Physician> physician = physicianRepository.findById(id);
        if(physician.isEmpty()){
            throw new EntityNotExistsException(Constants.physicianNotFound);
        }
        physicianRepository.delete(physician.get());
    }
}
