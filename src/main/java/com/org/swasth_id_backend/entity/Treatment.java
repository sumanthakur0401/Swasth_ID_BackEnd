package com.org.swasth_id_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "treatments")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Treatment extends BaseEntity{


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;

    @Column(name = "diagnosis", nullable = false)
    private String diagnosis;

    @Column(name = "notes", length = 2000)
    private String notes;

    @Column(name = "is_ongoing", nullable = false)
    private boolean isOngoing;

    @Column(name = "follow_up_required", nullable = false)
    private boolean followUpRequired;

    @Column(name = "follow_up_date")
    @Temporal(TemporalType.DATE)
    private Date followUpDate;

    @Column(name = "severity", nullable = false)
    private String severity;
}