package com.org.swasth_id_backend.service;

import com.org.swasth_id_backend.dto.DoctorDto;
import com.org.swasth_id_backend.exception.ResourceAlreadyExistsException;
import com.org.swasth_id_backend.exception.ResourceNotFoundException;

import java.util.List;
import java.util.UUID;

public interface DoctorService {
    DoctorDto createDoctor(DoctorDto doctorDto) throws ResourceAlreadyExistsException, ResourceNotFoundException;
    DoctorDto getDoctorById(UUID id) throws ResourceNotFoundException;
    List<DoctorDto> getAllDoctors();
    DoctorDto updateDoctor(UUID id, DoctorDto doctorDto) throws ResourceNotFoundException;
    void deleteDoctor(UUID id) throws ResourceNotFoundException;
    DoctorDto getDoctorByUserId(UUID userId) throws ResourceNotFoundException;
    List<DoctorDto> searchDoctors(String specialization, String hospitalName);
}
