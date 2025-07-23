package com.org.swasth_id_backend.service.serviceImpl;

import com.org.swasth_id_backend.dto.UserDto;
import com.org.swasth_id_backend.dto.UserUpdateDto;
import com.org.swasth_id_backend.entity.User;
import com.org.swasth_id_backend.exception.ResourceNotFoundException;
import com.org.swasth_id_backend.repo.UserRepository;
import com.org.swasth_id_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto registerUser(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto updateUserProfile(Long userId, UserUpdateDto updateDto) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> findUserById(Long userId) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> findUserByEmail(String email) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> findUserByUsername(String username) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> findUserByPatientId(String patientId) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserDto> findAllUsers(Pageable pageable) {
        return null;
    }

    @Override
    public void deactivateUser(Long userId) {
        
    }

    @Override
    public void reactivateUser(Long userId) {
        
    }

    @Override
    public void deleteUser(Long userId) {
       
    }
}