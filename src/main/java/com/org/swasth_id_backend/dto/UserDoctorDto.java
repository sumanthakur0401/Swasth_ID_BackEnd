package com.org.swasth_id_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDoctorDto {
    private UserDto userDto;
    private DoctorDto doctorDto;
}
