package com.org.swasth_id_backend.service.serviceImpl;

import com.org.swasth_id_backend.dto.DoctorDto;
import com.org.swasth_id_backend.entity.Doctor;
import com.org.swasth_id_backend.exception.DuplicateResourceException;
import com.org.swasth_id_backend.exception.ResourceNotFoundException;
import com.org.swasth_id_backend.repo.DoctorRepository;
import com.org.swasth_id_backend.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DoctorServiceImpl implements DoctorService {


    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public DoctorDto createDoctor(DoctorDto dto) {
        if (doctorRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new DuplicateResourceException("Username already exists: " + dto.getUsername());
        }
        if (doctorRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Email already exists: " + dto.getEmail());
        }
        if (dto.getRegistrationNumber() != null &&
                doctorRepository.findByRegistrationNumber(dto.getRegistrationNumber()).isPresent()) {
            throw new DuplicateResourceException("Registration number already exists: " + dto.getRegistrationNumber());
        }

        Doctor doctor = DoctorDto.toEntity(dto);
        doctor.setPassword(passwordEncoder.encode(dto.getPassword()));
        Doctor saved = doctorRepository.save(doctor);
        return DoctorDto.fromEntity(saved);
    }

    @Override
    public DoctorDto getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + id));
        return DoctorDto.fromEntity(doctor);
    }

    @Override
    public List<DoctorDto> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(DoctorDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public DoctorDto updateDoctor(Long id, DoctorDto dto) {
        Doctor existing = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + id));

        if (!existing.getUsername().equals(dto.getUsername()) &&
                doctorRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new DuplicateResourceException("Username already exists: " + dto.getUsername());
        }

        if (!existing.getEmail().equals(dto.getEmail()) &&
                doctorRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Email already exists: " + dto.getEmail());
        }

        if (dto.getRegistrationNumber() != null &&
                !dto.getRegistrationNumber().equals(existing.getRegistrationNumber()) &&
                doctorRepository.findByRegistrationNumber(dto.getRegistrationNumber()).isPresent()) {
            throw new DuplicateResourceException("Registration number already exists: " + dto.getRegistrationNumber());
        }

        existing.setUsername(dto.getUsername());
        existing.setEmail(dto.getEmail());
        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setSpecialization(dto.getSpecialization());
        existing.setPhoneNumber(dto.getPhoneNumber());
        existing.setDegreeFileUrl(dto.getDegreeFileUrl());
        existing.setRegistrationNumber(dto.getRegistrationNumber());
        existing.setRole(dto.getRole());

        Doctor updated = doctorRepository.save(existing);
        return DoctorDto.fromEntity(updated);
    }

    @Override
    public void deleteDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + id));
        doctorRepository.delete(doctor);
    }

    @Override
    public List<DoctorDto> getDoctorsBySpecialization(String specialization) {
        return doctorRepository.findBySpecializationIgnoreCase(specialization).stream()
                .map(DoctorDto::fromEntity)
                .collect(Collectors.toList());
    }

}
