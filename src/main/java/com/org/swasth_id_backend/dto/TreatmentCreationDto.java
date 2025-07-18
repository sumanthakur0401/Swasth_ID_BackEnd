package com.org.swasth_id_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.org.swasth_id_backend.entity.Treatment;
import com.org.swasth_id_backend.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for creating a new treatment record.
 * All essential fields are marked as required.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentCreationDto {

    @NotBlank(message = "Diagnosis is required")
    @Size(max = 255, message = "Diagnosis cannot exceed 255 characters")
    private String diagnosis;

    @NotNull(message = "Prescribed medicines list cannot be null")
    @Size(min = 1, message = "At least one medicine must be prescribed")
    private List<@NotBlank(message = "Medicine name cannot be blank") String> prescribedMedicines;

    private List<@NotBlank(message = "Test name cannot be blank") String> recommendedTests;

    @Size(max = 2000, message = "Notes cannot exceed 2000 characters")
    private String notes;

    @NotNull(message = "Date of treatment is required")
    private LocalDateTime dateOfTreatment;

    @JsonProperty("isOngoing")
    private boolean isOngoing = true;

    private boolean followUpRequired = false;

    private LocalDateTime followUpDate;

    @NotBlank(message = "Severity is required")
    private String severity;


    /**
     * Converts this DTO to a new Treatment entity, associating it with the provided user.
     *
     * @param user The User entity to link to this treatment.
     * @return A new Treatment entity, ready to be saved.
     */
    public Treatment toEntity(User user) {
        return Treatment.builder()
                .user(user) // Link the treatment to the user
                .diagnosis(this.diagnosis)
                .prescribedMedicines(this.prescribedMedicines)
                .recommendedTests(this.recommendedTests)
                .notes(this.notes)
                .dateOfTreatment(this.dateOfTreatment)
                .isOngoing(this.isOngoing)
                .followUpRequired(this.followUpRequired)
                .followUpDate(this.followUpDate)
                .severity(this.severity)
                .build();
    }
}