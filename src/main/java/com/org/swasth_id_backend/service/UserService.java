package com.org.swasth_id_backend.service;

import com.org.swasth_id_backend.dto.UserDto;
import com.org.swasth_id_backend.dto.UserUpdateDto; // A DTO for updating user profiles
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service layer for managing users.
 */
public interface UserService {

    /**
     * Registers a new user in the system.
     * @param userDto DTO containing the new user's information.
     * @return The created user's data.
     */
    UserDto registerUser(UserDto userDto);

    /**
     * Updates the profile information of an existing user.
     * @param userId The ID of the user to update.
     * @param userUpdateDto DTO containing the fields to update.
     * @return The updated user's data.
     */
    UserDto updateUserProfile(Long userId, UserUpdateDto userUpdateDto);

    /**
     * Finds a user by their unique database ID.
     * @param userId The user's ID.
     * @return An Optional containing the UserDto if found.
     */
    Optional<UserDto> findUserById(Long userId);

    /**
     * Finds a user by their unique email address.
     * @param email The user's email.
     * @return An Optional containing the UserDto if found.
     */
    Optional<UserDto> findUserByEmail(String email);

    /**
     * Finds a user by their unique username.
     * @param username The user's username.
     * @return An Optional containing the UserDto if found.
     */
    Optional<UserDto> findUserByUsername(String username);

    /**
     * Finds a user by their unique patient ID (e.g., SWA123456).
     * @param patientId The user's patient ID.
     * @return An Optional containing the UserDto if found.
     */
    Optional<UserDto> findUserByPatientId(String patientId);

    /**
     * Retrieves all users with pagination.
     * @param pageable Pagination information (page number, size, sort).
     * @return A paginated list of users.
     */
    Page<UserDto> findAllUsers(Pageable pageable);

    /**
     * Deactivates a user account (soft delete).
     * @param userId The ID of the user to deactivate.
     */
    void deactivateUser(Long userId);

    /**
     * Reactivates a user account.
     * @param userId The ID of the user to reactivate.
     */
    void reactivateUser(Long userId);

    /**
     * Permanently deletes a user from the system.
     * @param userId The ID of the user to delete.
     */
    void deleteUser(Long userId);
}