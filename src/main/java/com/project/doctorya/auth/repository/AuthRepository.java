package com.project.doctorya.auth.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.doctorya.auth.entity.Auth;

public interface AuthRepository extends JpaRepository<Auth, UUID> {
    Optional<Auth> findByIdentification(String identification);
}
