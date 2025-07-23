package com.org.swasth_id_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "consultations")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consultation extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private String visitReason;
    private String diagnosis;
    private String notes;
    private LocalDate visitDate;
    private Boolean isFollowUpRequired;
    private LocalDate followUpDate;

    @OneToMany(mappedBy = "consultation", cascade = CascadeType.ALL)
    private List<Prescription> prescriptions;

    @OneToMany(mappedBy = "consultation", cascade = CascadeType.ALL)
    private List<TestReport> testReports;

    @OneToOne(mappedBy = "consultation", cascade = CascadeType.ALL)
    private FollowUp followUp;
}