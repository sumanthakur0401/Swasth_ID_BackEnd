package com.org.swasth_id_backend.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;

    private String username;

    private String email;

    private String password;

    private Integer age;

    private String bloodGroup;

    private String patientId;

    private String role;
}
