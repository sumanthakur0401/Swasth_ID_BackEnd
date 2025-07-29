package com.org.swasth_id_backend.controller;

import com.org.swasth_id_backend.dto.PatientDto;
import com.org.swasth_id_backend.dto.UserPatientDto;
import com.org.swasth_id_backend.exception.ResourceNotFoundException;
import com.org.swasth_id_backend.exception.UserAlreadyExistsException;
import com.org.swasth_id_backend.service.PatientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Patient Controller", description = "CRUD operations for Patients")
public class PatientController {

    private final PatientService patientService;


    @PostMapping
    public ResponseEntity<UserPatientDto> createPatient(@RequestBody UserPatientDto userPatientDto) throws UserAlreadyExistsException, ResourceNotFoundException {
        return new ResponseEntity<>(patientService.createPatient(userPatientDto), HttpStatus.CREATED);
    }

    // @PostMapping
    // public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patientDto) throws ResourceAlreadyExistsException, ResourceNotFoundException {
    //     log.info("Creating patient for userId: {}", patientDto.getId());
    //     return new ResponseEntity<>(patientService.createPatient(patientDto), HttpStatus.CREATED);
    // }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable UUID id) throws ResourceNotFoundException {
        log.info("Fetching patient with ID: {}", id);
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<PatientDto> getPatientByUserId(@PathVariable UUID userId) throws ResourceNotFoundException {
        log.info("Fetching patient by userId: {}", userId);
        return ResponseEntity.ok(patientService.getPatientByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatients() {
        log.info("Fetching all patients");
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDto> updatePatient(@PathVariable UUID id, @RequestBody PatientDto patientDto) throws ResourceNotFoundException {
        log.info("Updating patient with ID: {}", id);
        return ResponseEntity.ok(patientService.updatePatient(id, patientDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id) throws ResourceNotFoundException {
        log.info("Deleting patient with ID: {}", id);
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/bloodGroup")
    public ResponseEntity<List<PatientDto>> getPatientsByBloodGroup(@RequestParam String bloodGroup) {
        log.info("Searching patients by blood group: {}", bloodGroup);
        return ResponseEntity.ok(patientService.getPatientsByBloodGroup(bloodGroup));
    }
}