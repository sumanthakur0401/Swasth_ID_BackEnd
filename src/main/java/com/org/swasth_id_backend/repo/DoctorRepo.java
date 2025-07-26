package com.org.swasth_id_backend.repo;

import com.org.swasth_id_backend.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DoctorRepo extends JpaRepository<Doctor, UUID> {
    boolean existsByLicenseNumber(String licenseNumber);
    Optional<Doctor> findByUserId(UUID userId);
    List<Doctor> findBySpecializationContainingIgnoreCaseAndHospitalNameContainingIgnoreCase(String specialization, String hospitalName);
    boolean existsByPhoneNumber(String phoneNumber);
}
