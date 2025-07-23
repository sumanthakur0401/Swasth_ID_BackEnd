package com.org.swasth_id_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "prescriptions")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prescription extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "consultation_id")
    private Consultation consultation;

    private String medicineName;
    private String dosage;
    private String duration;
    private String instructions;
}
