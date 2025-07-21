package com.org.swasth_id_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "doctors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Long doctorId;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false, length = 100)
    private String specialization;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "degree_file_urlxs", nullable = false, length = 500)
    private String degreeFileUrl;

    @Column(name = "registration_number", unique = true, length = 50)
    private String registrationNumber;

    @Column(length = 20)
    private String role = "DOCTOR";

}