package com.org.swasth_id_backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.org.swasth_id_backend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID userId;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String dob;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer age;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean isActive;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean isVerified;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean isOtpVerified;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime otpLastUpdate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime lastUpdate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<Role> roles;

}
