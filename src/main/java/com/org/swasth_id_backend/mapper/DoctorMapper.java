package com.org.swasth_id_backend.mapper;

import com.org.swasth_id_backend.dto.DoctorDto;
import com.org.swasth_id_backend.entity.BaseEntity;
import com.org.swasth_id_backend.entity.Doctor;
import com.org.swasth_id_backend.entity.User;

import java.util.stream.Collectors;

public class DoctorMapper {

    public static DoctorDto toDto(Doctor doctor) {
        DoctorDto dto = new DoctorDto();
        dto.setId(doctor.getId());
        dto.setSpecialization(doctor.getSpecialization());
        dto.setPhoneNumber(doctor.getPhoneNumber());
        dto.setLicenseNumber(doctor.getLicenseNumber());
        dto.setYearsOfExperience(doctor.getYearsOfExperience());
        dto.setHospitalName(doctor.getHospitalName());
        dto.setAvailableTimings(doctor.getAvailableTimings());

        if (doctor.getUser() != null) {
            dto.setUserId(doctor.getUser().getId());
        }

        if (doctor.getConsultations() != null) {
            dto.setConsultationIds(
                    doctor.getConsultations().stream()
                            .map(BaseEntity::getId)
                            .collect(Collectors.toList())
            );
        }

        if (doctor.getDoctorPatients() != null) {
            dto.setDoctorPatientIds(
                    doctor.getDoctorPatients().stream()
                            .map(BaseEntity::getId)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

    public static Doctor toEntity(DoctorDto dto) {
        Doctor doctor = new Doctor();
        doctor.setId(dto.getId());
        doctor.setSpecialization(dto.getSpecialization());
        doctor.setPhoneNumber(dto.getPhoneNumber());
        doctor.setLicenseNumber(dto.getLicenseNumber());
        doctor.setYearsOfExperience(dto.getYearsOfExperience());
        doctor.setHospitalName(dto.getHospitalName());
        doctor.setAvailableTimings(dto.getAvailableTimings());

        return doctor;
    }
}
