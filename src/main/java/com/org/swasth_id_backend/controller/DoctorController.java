package com.org.swasth_id_backend.controller;

import com.org.swasth_id_backend.dto.DoctorDto;
import com.org.swasth_id_backend.exception.ResourceAlreadyExistsException;
import com.org.swasth_id_backend.exception.ResourceNotFoundException;
import com.org.swasth_id_backend.service.DoctorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
@Tag(name = "Doctor Controller")
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<DoctorDto> createDoctor(@RequestBody DoctorDto doctorDto) throws ResourceAlreadyExistsException, ResourceNotFoundException {
        log.info("Received request to create doctor: {}", doctorDto);
        DoctorDto created = doctorService.createDoctor(doctorDto);
        log.info("Doctor created with ID: {}", created.getId());
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDto> getDoctorById(@PathVariable UUID id) throws ResourceNotFoundException {
        log.info("Fetching doctor by ID: {}", id);
        DoctorDto doctor = doctorService.getDoctorById(id);
        log.debug("Doctor found: {}", doctor);
        return ResponseEntity.ok(doctor);
    }

    @GetMapping
    public ResponseEntity<List<DoctorDto>> getAllDoctors() {
        log.info("Fetching all doctors");
        List<DoctorDto> doctors = doctorService.getAllDoctors();
        log.debug("Total doctors found: {}", doctors.size());
        return ResponseEntity.ok(doctors);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorDto> updateDoctor(@PathVariable UUID id, @RequestBody DoctorDto doctorDto) throws ResourceNotFoundException {
        log.info("Updating doctor with ID: {}", id);
        DoctorDto updated = doctorService.updateDoctor(id, doctorDto);
        log.info("Doctor updated: {}", updated);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable UUID id) throws ResourceNotFoundException {
        log.warn("Deleting doctor with ID: {}", id);
        doctorService.deleteDoctor(id);
        log.info("Doctor with ID {} deleted", id);
        return ResponseEntity.ok("Doctor deleted successfully.");
    }

    @GetMapping("/search")
    public ResponseEntity<List<DoctorDto>> searchDoctors(
            @RequestParam(required = false) String specialization,
            @RequestParam(required = false) String hospital
    ) {
        log.info("Searching doctors with specialization: {} and hospital: {}", specialization, hospital);
        List<DoctorDto> results = doctorService.searchDoctors(specialization, hospital);
        log.debug("Doctors matched: {}", results.size());
        return ResponseEntity.ok(results);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<DoctorDto> getDoctorByUserId(@PathVariable UUID userId) throws ResourceNotFoundException {
        log.info("Fetching doctor by userId: {}", userId);
        DoctorDto doctor = doctorService.getDoctorByUserId(userId);
        log.debug("Doctor for userId {}: {}", userId, doctor);
        return ResponseEntity.ok(doctor);
    }
}
