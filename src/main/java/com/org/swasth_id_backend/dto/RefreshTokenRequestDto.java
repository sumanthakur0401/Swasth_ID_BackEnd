package com.org.swasth_id_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenRequestDto {

    private String refreshToken;
    private String email;
}
