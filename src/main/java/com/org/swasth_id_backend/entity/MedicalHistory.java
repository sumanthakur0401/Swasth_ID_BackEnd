package com.org.swasth_id_backend.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "medical_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long historyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;

    @Column(name = "diagnosis", nullable = false)
    private String diagnosis;

    @ElementCollection
    @CollectionTable(name = "history_prescribed_medicines", joinColumns = @JoinColumn(name = "history_id"))
    @Column(name = "medicine", nullable = false)
    private List<String> prescribedMedicines;

    @ElementCollection
    @CollectionTable(name = "history_recommended_tests", joinColumns = @JoinColumn(name = "history_id"))
    @Column(name = "test")
    private List<String> recommendedTests;

    @Column(name = "notes", length = 2000)
    private String notes;

    @Column(name = "date_of_treatment", nullable = false)
    private LocalDateTime dateOfTreatment;

    @Column(name = "severity", nullable = false)
    private String severity;


}
