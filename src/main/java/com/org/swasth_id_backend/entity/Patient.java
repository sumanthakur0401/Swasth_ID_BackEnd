package com.org.swasth_id_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "patients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Patient extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = true)
    @ToString.Exclude
    private User user;

    @Column(name = "gender", length = 10)
    private String gender;

    @Column(name = "contact_number", length = 15)
    private String contactNumber;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "emergency_contact_name", length = 100)
    private String emergencyContactName;

    @Column(name = "emergency_contact_number", length = 15)
    private String emergencyContactNumber;

    @Column(name = "known_allergies", length = 500)
    private String knownAllergies;

    @Column(name = "existing_conditions", length = 500)
    private String existingConditions;

    @Column(name = "insurance_number", length = 50, unique = true, nullable = true)
    private String insuranceNumber;

    @Column(name = "blood_group", length = 5)
    private String bloodGroup;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<DoctorPatient> doctorPatients = new ArrayList<>();

}
