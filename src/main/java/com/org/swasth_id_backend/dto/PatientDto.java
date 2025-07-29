package com.org.swasth_id_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
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
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID userId;
    private List<UUID> consultationIds = new ArrayList<>();
    private List<UUID> doctorPatientIds = new ArrayList<>();
}
