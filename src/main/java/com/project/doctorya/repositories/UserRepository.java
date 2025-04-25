package com.project.doctorya.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.doctorya.models.User;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByIdentification(String identification);
}
