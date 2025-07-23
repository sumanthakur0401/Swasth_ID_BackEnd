package com.org.swasth_id_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "test_reports")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestReport extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "consultation_id")
    private Consultation consultation;

    private String testType;
    private String resultSummary;
    private String reportFileUrl;
    private LocalDate testDate;
}