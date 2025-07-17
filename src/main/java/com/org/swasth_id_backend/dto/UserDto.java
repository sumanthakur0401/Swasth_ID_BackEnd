package com.org.swasth_id_backend.dto;

import com.org.swasth_id_backend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String username;
    private String email;
    private String password; // Only used for registration input
    private Integer age;
    private String bloodGroup;
    private String patientId;
    private String role;
    private List<TreatmentDto> treatments;

    /**
     * Converts a User entity to a UserDto.
     * Also converts the associated treatments.
     */
    public static UserDto fromEntity(User user) {
        if (user == null) return null;

        List<TreatmentDto> treatmentDtos = user.getTreatments() != null
                ? user.getTreatments().stream().map(TreatmentDto::fromEntity).collect(Collectors.toList())
                : Collections.emptyList();

        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                // IMPORTANT: Never expose password in the DTO response
                .age(user.getAge())
                .bloodGroup(user.getBloodGroup())
                .patientId(user.getPatientId())
                .role(user.getRole())
                .treatments(treatmentDtos)
                .build();
    }

    /**
     * Converts this DTO to a User entity for registration.
     */
    public User toEntity() {
        return User.builder()
                .username(this.username)
                .email(this.email)
                .password(this.password) // Password will be encoded by the service
                .age(this.age)
                .bloodGroup(this.bloodGroup)
                .build();
    }
}