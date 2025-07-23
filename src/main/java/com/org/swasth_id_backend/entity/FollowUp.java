package com.org.swasth_id_backend.entity;

import com.org.swasth_id_backend.utils.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "follow_ups")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowUp extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "consultation_id")
    private Consultation consultation;

    private LocalDate scheduledDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String notes;


}