// com/org/swasth_id_backend/dto/TreatmentDto.java

package com.org.swasth_id_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.org.swasth_id_backend.entity.Treatment;
import com.org.swasth_id_backend.entity.User; // Import User entity
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TreatmentDto {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotBlank(message = "Diagnosis is required")
    private String diagnosis;

    @Size(min = 1, message = "At least one medicine must be prescribed")
    private List<@NotBlank String> prescribedMedicines;

    private List<@NotBlank String> recommendedTests;

    @Size(max = 2000, message = "Notes cannot exceed 2000 characters")
    private String notes;

    @NotNull(message = "Date of treatment is required")
    private LocalDateTime dateOfTreatment;

    @JsonProperty("isOngoing")
    private boolean isOngoing;

    private boolean followUpRequired;

    private LocalDateTime followUpDate;

    @NotBlank(message = "Severity is required")
    private String severity;


    // --- MAPPING LOGIC ADDED HERE ---

    /**
     * Creates a TreatmentDto from a Treatment entity.
     *
     * @param treatment The Treatment entity to convert.
     * @return A new TreatmentDto instance.
     */
    public static TreatmentDto fromEntity(Treatment treatment) {
        if (treatment == null) {
            return null;
        }

        return TreatmentDto.builder()
                .userId(treatment.getUser().getId()) // Get user ID from the associated User
                .diagnosis(treatment.getDiagnosis())
                .prescribedMedicines(treatment.getPrescribedMedicines())
                .recommendedTests(treatment.getRecommendedTests())
                .notes(treatment.getNotes())
                .dateOfTreatment(treatment.getDateOfTreatment())
                .isOngoing(treatment.isOngoing())
                .followUpRequired(treatment.isFollowUpRequired())
                .followUpDate(treatment.getFollowUpDate())
                .severity(treatment.getSeverity())
                .build();
    }

    /**
     * Converts this DTO to a Treatment entity.
     *
     * @param user The User entity to associate with this treatment.
     * @return A new Treatment entity.
     */
    public Treatment toEntity(User user) {
        return Treatment.builder()
                .user(user) // Set the full user object
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