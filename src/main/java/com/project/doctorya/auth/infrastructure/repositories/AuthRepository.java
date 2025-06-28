package com.project.doctorya.auth.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.doctorya.auth.domain.model.AuthModel;
import com.project.doctorya.auth.domain.repository.IAuthRepository;
import com.project.doctorya.auth.infrastructure.entity.Auth;
import com.project.doctorya.auth.infrastructure.mapper.AuthMapper;

@Repository
@Primary
public class AuthRepository implements IAuthRepository {

    @Autowired
    private IAuthJpaRepository authRepository;

    @Override
    public List<AuthModel> get() {
        List<Auth> auth = authRepository.findAll();
        return auth.stream().map(AuthMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public AuthModel getByIdentification(String identification) {
        Optional<Auth> auth = authRepository.findByIdentification(identification);
        if(auth.isEmpty()){
            return null;
        }
        return AuthMapper.toDomain(auth.get());
    }

    @Override
    public AuthModel create(AuthModel authModel) {
        Auth auth = AuthMapper.toEntity(authModel);
        Auth response = authRepository.save(auth);
        return AuthMapper.toDomain(response);
    }
    
}
