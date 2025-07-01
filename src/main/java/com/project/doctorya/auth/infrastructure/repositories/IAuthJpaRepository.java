package com.project.doctorya.auth.infrastructure.repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.doctorya.auth.infrastructure.entities.Auth;

public interface IAuthJpaRepository extends JpaRepository<Auth, UUID> {
    Optional<Auth> findByIdentification(String identification);
}
