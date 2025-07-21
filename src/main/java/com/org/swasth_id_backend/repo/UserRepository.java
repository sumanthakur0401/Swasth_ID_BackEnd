package com.org.swasth_id_backend.repo;

import com.org.swasth_id_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    // --- METHOD ADDED HERE ---
    Optional<User> findByPatientId(String patientId);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}