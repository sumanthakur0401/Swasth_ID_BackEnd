package com.org.swasth_id_backend.dto;

import com.org.swasth_id_backend.entity.Doctor;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorDto {

    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String specialization;
    private String phoneNumber;
    private String degreeFileUrl;
    private String registrationNumber;
    private String role;


    public static DoctorDto fromEntity(Doctor doctor) {
        return DoctorDto.builder()
                .username(doctor.getUsername())
                .email(doctor.getEmail())
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .specialization(doctor.getSpecialization())
                .phoneNumber(doctor.getPhoneNumber())
                .degreeFileUrl(doctor.getDegreeFileUrl())
                .registrationNumber(doctor.getRegistrationNumber())
                .role(doctor.getRole())
                .build();
    }


    public static Doctor toEntity(DoctorDto dto) {
        return Doctor.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .password(dto.getPassword())
                .specialization(dto.getSpecialization())
                .phoneNumber(dto.getPhoneNumber())
                .degreeFileUrl(dto.getDegreeFileUrl())
                .registrationNumber(dto.getRegistrationNumber())
                .role(dto.getRole())
                .build();
    }
}
