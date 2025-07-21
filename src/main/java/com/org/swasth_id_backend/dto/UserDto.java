package com.org.swasth_id_backend.dto;

import com.org.swasth_id_backend.entity.MedicalHistory;
import com.org.swasth_id_backend.entity.Treatment;
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
    private List<MedicalHistoryDto> history;

    /**
     * Converts a User entity to a UserDto.
     * Also converts the associated treatments and medical history.
     */
//    public static UserDto fromEntity(User user) {
//        if (user == null) return null;
//
//        List<TreatmentDto> treatmentDtos = user.getTreatments() != null
//                ? user.getTreatments().stream()
//                .map(TreatmentDto::fromEntity)
//                .collect(Collectors.toList())
//                : Collections.emptyList();
//
//        List<MedicalHistoryDto> historyDtos = user.getHistory()!= null
//                ? user.getHistory().stream()
//                .map(MedicalHistoryDto::fromEntity)
//                .collect(Collectors.toList())
//                : Collections.emptyList();
//
//        return UserDto.builder()
//                .id(user.getId())
//                .username(user.getUsername())
//                .email(user.getEmail())
//                .age(user.getAge())
//                .bloodGroup(user.getBloodGroup())
//                .patientId(user.getPatientId())
//                .role(user.getRole())
//                .treatments(treatmentDtos)
//                .history(historyDtos)
//                .build();
//    }

    /**
     * Converts this DTO to a User entity for registration.
     * Only includes treatments, since medical history is generated post-treatment.
     */
//    public User toEntity() {
//        User user = User.builder()
//                .username(this.username)
//                .email(this.email)
//                .password(this.password)
//                .age(this.age)
//                .bloodGroup(this.bloodGroup)
//                .build();
//
//        if (this.treatments != null) {
//            List<Treatment> treatmentEntities = this.treatments.stream()
//                    .map(dto -> {
//                        Treatment treatment = dto.toEntity(user); // assuming this method exists
//                        treatment.setUser(user); // establish FK link
//                        return treatment;
//                    })
//                    .collect(Collectors.toList());
//            user.setTreatments(treatmentEntities);
//        }
//
//        // Medical history is typically not set during registration — skip here unless necessary
//
//        return user;
//    }
}
