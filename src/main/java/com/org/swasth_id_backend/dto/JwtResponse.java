package com.org.swasth_id_backend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtResponse {

    private String accessToken;
    private String refreshToken;
    private String email;
    private Short userId;
    private String username;
}
