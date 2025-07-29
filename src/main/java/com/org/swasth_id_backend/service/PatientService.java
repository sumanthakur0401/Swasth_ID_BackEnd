package com.org.swasth_id_backend.service;

import com.org.swasth_id_backend.dto.PatientDto;
import com.org.swasth_id_backend.dto.UserPatientDto;
import com.org.swasth_id_backend.exception.ResourceAlreadyExistsException;
import com.org.swasth_id_backend.exception.ResourceNotFoundException;
import com.org.swasth_id_backend.exception.UserAlreadyExistsException;

import java.util.List;
import java.util.UUID;

public interface PatientService {
    UserPatientDto createPatient(UserPatientDto userPatientDto) throws ResourceNotFoundException, UserAlreadyExistsException;

    PatientDto getPatientById(UUID id) throws ResourceNotFoundException;

    PatientDto getPatientByUserId(UUID userId) throws ResourceNotFoundException;

    PatientDto updatePatient(UUID id, PatientDto patientDTO) throws ResourceNotFoundException;

    void deletePatient(UUID id) throws ResourceNotFoundException;

    List<PatientDto> getAllPatients();

    List<PatientDto> getPatientsByBloodGroup(String bloodGroup);
}