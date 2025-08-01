package com.org.swasth_id_backend.repo;

import com.org.swasth_id_backend.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface DoctorRepo extends JpaRepository<Doctor, UUID> {
    boolean existsByLicenseNumber(String licenseNumber);
    boolean existsByPhoneNumber(String phoneNumber);
}
