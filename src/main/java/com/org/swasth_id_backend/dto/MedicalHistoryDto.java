package com.org.swasth_id_backend.dto;

import com.org.swasth_id_backend.entity.MedicalHistory;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalHistoryDto {

    @Schema(description = "User ID associated with the medical history")
    @NotNull(message = "User ID is required")
    private Long userId;

    @Schema(description = "Diagnosis description")
    @NotBlank(message = "Diagnosis is required")
    private String diagnosis;

    @Schema(description = "List of prescribed medicines")
    private List<@NotBlank(message = "Medicine name can't be blank") String> prescribedMedicines;

    @Schema(description = "List of recommended tests")
    private List<@NotBlank(message = "Test name can't be blank") String> recommendedTests;

    @Schema(description = "Doctor's notes")
    private String notes;

    @Schema(description = "Date of treatment")
    @NotNull(message = "Date of treatment is required")
    private LocalDateTime dateOfTreatment;

    @Schema(description = "Severity level")
    @NotBlank(message = "Severity is required")
    private String severity;

    // --- Mapper Methods ---
//    public static MedicalHistoryDto fromEntity(MedicalHistory history) {
//        return MedicalHistoryDto.builder()
//                .userId(history.getUser().getId())
//                .diagnosis(history.getDiagnosis())
//                .prescribedMedicines(history.getPrescribedMedicines())
//                .recommendedTests(history.getRecommendedTests())
//                .notes(history.getNotes())
//                .dateOfTreatment(history.getDateOfTreatment())
//                .severity(history.getSeverity())
//                .build();
//    }

//    public static MedicalHistory toEntity(MedicalHistoryDto dto) {
//        return MedicalHistory.builder()
//                .diagnosis(dto.getDiagnosis())
//                .prescribedMedicines(dto.getPrescribedMedicines())
//                .recommendedTests(dto.getRecommendedTests())
//                .notes(dto.getNotes())
//                .dateOfTreatment(dto.getDateOfTreatment())
//                .severity(dto.getSeverity())
//                .build();
//    }

}
