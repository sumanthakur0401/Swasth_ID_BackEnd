package com.org.swasth_id_backend.service;

import com.org.swasth_id_backend.dto.DoctorDto;

import java.util.List;

public interface DoctorService {
    DoctorDto createDoctor(DoctorDto doctorDTO);
    DoctorDto getDoctorById(Long id);
    List<DoctorDto> getAllDoctors();
    DoctorDto updateDoctor(Long id, DoctorDto doctorDTO);
    void deleteDoctor(Long id);
    List<DoctorDto> getDoctorsBySpecialization(String specialization);
}
