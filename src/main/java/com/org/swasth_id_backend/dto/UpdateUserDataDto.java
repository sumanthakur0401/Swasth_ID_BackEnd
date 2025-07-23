package com.org.swasth_id_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateUserDataDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Short userId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String username;
    private String firstName;
    private String lastName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String phoneNumber;
    private Boolean isActive;
    private Boolean isVerified;
    private Boolean isOtpVerified;

    private LocalDateTime otpLastUpdate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String email;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime lastUpdate;

}
