package com.org.swasth_id_backend.repo;

import com.org.swasth_id_backend.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface PatientRepo extends JpaRepository<Patient, UUID> {
    Optional<Patient> findByUserId(UUID userId);
    List<Patient> findByBloodGroupIgnoreCase(String bloodGroup);
}
