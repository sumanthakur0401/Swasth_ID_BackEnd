package com.org.swasth_id_backend.repo;

import com.org.swasth_id_backend.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
    Optional<Doctor> findByUsername(String username);
    Optional<Doctor> findByEmail(String email);
    Optional<Doctor> findByRegistrationNumber(String registrationNumber);
    List<Doctor> findBySpecializationIgnoreCase(String specialization);
}
