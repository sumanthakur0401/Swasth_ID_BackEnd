package com.org.swasth_id_backend.service;

import com.gyanpath.security.dto.PasswordResetOtpDto;
import com.gyanpath.security.entity.PasswordResetOtp;
import com.gyanpath.security.exception.InvalidOtpException;

import java.util.List;

public interface PasswordResetOtpService {

    public PasswordResetOtp createOtp(String email);

    public boolean validateOtp(String email, String otp) throws InvalidOtpException;

    public void deleteOtpByEmail(String email);

    public void deleteOtpByOtp(String otp);

    List<PasswordResetOtpDto> getAllOtpList();
}
