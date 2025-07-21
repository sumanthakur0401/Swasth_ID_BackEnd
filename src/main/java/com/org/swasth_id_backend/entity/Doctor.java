package com.org.swasth_id_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Doctor extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false, length = 100)
    private String specialization;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "degree_file_url", nullable = false, length = 500)
    private String degreeFileUrl;

    @Column(name = "registration_number", unique = true, length = 50)
    private String registrationNumber;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<DoctorPatient> doctorPatients = new ArrayList<>();

}
