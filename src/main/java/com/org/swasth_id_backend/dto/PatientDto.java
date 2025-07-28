package com.org.swasth_id_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientDto {

    private UUID id;
    private String patientCode;
    private String gender;
    private String knownAllergies;
    private String existingConditions;
    private String emergencyContactName;
    private String emergencyContactNumber;
    private String insuranceProvider;
    private String insurancePolicyNumber;
    private String bloodGroup;
    private UUID userId;
    private List<UUID> consultationIds;
    private List<UUID> doctorPatientIds;
}
