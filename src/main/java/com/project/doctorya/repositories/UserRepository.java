package com.project.doctorya.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.doctorya.models.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByIdentification(String identification);
}
