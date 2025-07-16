package com.org.swasth_id_backend.dto;

import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TreatmentDto {

    @NotBlank(message = "Diagnosis is required")
    private String diagnosis;

    @Size(min = 1, message = "At least one medicine must be prescribed")
    private List<@NotBlank(message = "Medicine name must not be blank") String> prescribedMedicines;

    private List<@NotBlank(message = "Test name must not be blank") String> recommendedTests;

    @Size(max = 2000, message = "Notes cannot exceed 2000 characters")
    private String notes;

    @NotNull(message = "Date of treatment is required")
    private LocalDateTime dateOfTreatment;

    private boolean isOngoing;

    private boolean followUpRequired;

    private LocalDateTime followUpDate;

    @NotBlank(message = "Severity is required")
    private String severity;
}
