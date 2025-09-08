package com.project.doctorya.auth.infrastructure.repositories;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.doctorya.auth.domain.models.Auth;
import com.project.doctorya.auth.domain.repositories.IAuthRepository;
import com.project.doctorya.auth.infrastructure.entities.AuthEntity;
import com.project.doctorya.auth.infrastructure.mappers.AuthMapper;

@Repository
@Primary
public class AuthRepository implements IAuthRepository {

    @Autowired
    private IAuthJpaRepository authRepository;

    @Override
    public List<Auth> get() {
        List<AuthEntity> authEntity = authRepository.findAll();
        return authEntity.stream().map(AuthMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Auth getByIdentification(String identification) {
        Optional<AuthEntity> authEntity = authRepository.findByIdentification(identification);
        if(authEntity.isEmpty()){
            return null;
        }
        return AuthMapper.toDomain(authEntity.get());
    }

    @Override
    public Auth create(Auth auth) {
        AuthEntity authEntity = AuthMapper.toEntity(auth);
        AuthEntity response = authRepository.save(authEntity);
        return AuthMapper.toDomain(response);
    }
    
}
