package com.org.swasth_id_backend.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class DoctorDto {
    private UUID id;
    private String specialization;
    private String phoneNumber;
    private String licenseNumber;
    private Integer yearsOfExperience;
    private String hospitalName;
    private String availableTimings;
    private UUID userId;
    private List<UUID> consultationIds = List.of();
    private List<UUID> doctorPatientIds = List.of();
}