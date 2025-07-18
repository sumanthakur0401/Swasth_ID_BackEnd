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
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new IllegalArgumentException("Username is already taken");
        }
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Email is already in use");
        }

        User user = userDto.toEntity();


        // Encode the password before saving
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // Assign defaults
        user.setRole("USER");
        user.setPatientId("SWA" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        user.setEnabled(true);

        User savedUser = userRepository.save(user);
        return UserDto.fromEntity(savedUser);
    }

    @Override
    public UserDto updateUserProfile(Long userId, UserUpdateDto updateDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        // Perform a partial update
        if (updateDto.getUsername() != null) {
            user.setUsername(updateDto.getUsername());
        }
        if (updateDto.getAge() != null) {
            user.setAge(updateDto.getAge());
        }
        if (updateDto.getBloodGroup() != null) {
            user.setBloodGroup(updateDto.getBloodGroup());
        }

        User updatedUser = userRepository.save(user);
        return UserDto.fromEntity(updatedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> findUserById(Long userId) {
        return userRepository.findById(userId).map(UserDto::fromEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> findUserByEmail(String email) {
        return userRepository.findByEmail(email).map(UserDto::fromEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> findUserByUsername(String username) {
        return userRepository.findByUsername(username).map(UserDto::fromEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> findUserByPatientId(String patientId) {
        return userRepository.findByPatientId(patientId).map(UserDto::fromEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserDto> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserDto::fromEntity);
    }

    @Override
    public void deactivateUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        user.setEnabled(false);
        userRepository.save(user);
    }

    @Override
    public void reactivateUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }
        userRepository.deleteById(userId);
    }
}