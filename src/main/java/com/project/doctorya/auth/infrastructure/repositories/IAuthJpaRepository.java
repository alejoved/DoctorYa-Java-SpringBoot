package com.project.doctorya.auth.infrastructure.repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.doctorya.auth.infrastructure.entities.AuthEntity;

public interface IAuthJpaRepository extends JpaRepository<AuthEntity, UUID> {
    Optional<AuthEntity> findByIdentification(String identification);
}
