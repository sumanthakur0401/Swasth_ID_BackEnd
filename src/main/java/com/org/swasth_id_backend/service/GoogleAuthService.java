package com.org.swasth_id_backend.service;

import com.gyanpath.security.dto.JwtResponse;

public interface GoogleAuthService {

    JwtResponse handleCallBack(String code) throws Exception;
}
