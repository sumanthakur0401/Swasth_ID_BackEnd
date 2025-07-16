package com.org.swasth_id_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username; // Can be full name or a chosen username

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Integer age;

    @Column(name = "blood_group", length = 5)
    private String bloodGroup;

    @Column(name = "patient_id", unique = true, length = 20)
    private String patientId; // e.g., "SWA123456"

    @Column(length = 20)
    private String role; // Optional: For RBAC - "USER", "ADMIN", etc.

    private boolean enabled = true;

    // Additional metadata fields can be added as needed
}

