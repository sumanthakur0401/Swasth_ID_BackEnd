package com.org.swasth_id_backend.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "medical_histories")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalHistory extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private User user;

    @Column(name = "diagnosis", nullable = false)
    private String diagnosis;

    @Column(name = "notes", length = 2000)
    private String notes;


    @Column(name = "severity", nullable = false)
    private String severity;


}
