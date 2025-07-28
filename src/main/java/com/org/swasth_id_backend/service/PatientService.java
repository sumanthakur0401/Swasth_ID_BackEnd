package com.org.swasth_id_backend.service;

import com.org.swasth_id_backend.dto.PatientDto;
import com.org.swasth_id_backend.exception.ResourceAlreadyExistsException;
import com.org.swasth_id_backend.exception.ResourceNotFoundException;

import java.util.List;
import java.util.UUID;

public interface PatientService {
    PatientDto createPatient(PatientDto patientDTO) throws ResourceAlreadyExistsException, ResourceNotFoundException;
    PatientDto getPatientById(UUID id) throws ResourceNotFoundException;
    PatientDto getPatientByUserId(UUID userId) throws ResourceNotFoundException;
    PatientDto updatePatient(UUID id, PatientDto patientDTO) throws ResourceNotFoundException;
    void deletePatient(UUID id) throws ResourceNotFoundException;
    List<PatientDto> getAllPatients();

    List<PatientDto> getPatientsByBloodGroup(String bloodGroup);
}