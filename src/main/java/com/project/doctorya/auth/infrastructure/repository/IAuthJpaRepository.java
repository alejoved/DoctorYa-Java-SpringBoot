package com.project.doctorya.auth.infrastructure.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.doctorya.auth.infrastructure.entity.Auth;

public interface IAuthJpaRepository extends JpaRepository<Auth, UUID> {
    Optional<Auth> findByIdentification(String identification);
}
