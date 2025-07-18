package com.org.swasth_id_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for updating an existing treatment.
 * All fields are optional to allow for partial updates.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentUpdateDto {

    @Size(max = 255, message = "Diagnosis cannot exceed 255 characters")
    private String diagnosis;

    @Size(min = 1, message = "At least one medicine must be prescribed")
    private List<@NotBlank(message = "Medicine name cannot be blank") String> prescribedMedicines;

    private List<@NotBlank(message = "Test name cannot be blank") String> recommendedTests;

    @Size(max = 2000, message = "Notes cannot exceed 2000 characters")
    private String notes;

    private LocalDateTime dateOfTreatment;

    @JsonProperty("isOngoing")
    private Boolean isOngoing;

    private Boolean followUpRequired;

    private LocalDateTime followUpDate;

    private String severity;
}