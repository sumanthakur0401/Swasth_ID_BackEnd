package com.org.swasth_id_backend.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "treatment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "treatment_id")
    private Long treatmentId;

    @Column(name = "diagnosis", nullable = false)
    private String diagnosis;

    @ElementCollection
    @CollectionTable(name = "prescribed_medicines", joinColumns = @JoinColumn(name = "treatment_id"))
    @Column(name = "medicine", nullable = false)
    private List<String> prescribedMedicines;

    @ElementCollection
    @CollectionTable(name = "recommended_tests", joinColumns = @JoinColumn(name = "treatment_id"))
    @Column(name = "test")
    private List<String> recommendedTests;

    @Column(name = "notes", length = 2000)
    private String notes;

    @Column(name = "date_of_treatment", nullable = false)
    private LocalDateTime dateOfTreatment;

    @Column(name = "is_ongoing", nullable = false)
    private boolean isOngoing;

    @Column(name = "follow_up_required", nullable = false)
    private boolean followUpRequired;

    @Column(name = "follow_up_date")
    private LocalDateTime followUpDate;

    @Column(name = "severity", nullable = false)
    private String severity;
}
