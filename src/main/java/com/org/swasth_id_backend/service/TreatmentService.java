package com.org.swasth_id_backend.service;

import com.org.swasth_id_backend.dto.TreatmentCreationDto; // DTO for creating treatments
import com.org.swasth_id_backend.dto.TreatmentDto;
import com.org.swasth_id_backend.dto.TreatmentUpdateDto; // DTO for updating treatments

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service layer for managing medical treatments.
 */
public interface TreatmentService {

    /**
     * Adds a new treatment record for a specific user.
     * @param userId The ID of the user receiving the treatment.
     * @param treatmentCreationDto DTO with the details of the new treatment.
     * @return The created treatment's data.
     */
    TreatmentDto addTreatmentToUser(Long userId, TreatmentCreationDto treatmentCreationDto);

    /**
     * Updates the details of an existing treatment record.
     * @param treatmentId The ID of the treatment to update.
     * @param treatmentUpdateDto DTO with the fields to update.
     * @return The updated treatment's data.
     */
    TreatmentDto updateTreatment(Long treatmentId, TreatmentUpdateDto treatmentUpdateDto);

    /**
     * Finds a single treatment by its unique ID.
     * @param treatmentId The treatment's ID.
     * @return An Optional containing the TreatmentDto if found.
     */
    Optional<TreatmentDto> findTreatmentById(Long treatmentId);

    /**
     * Finds all treatment records for a specific user.
     * @param userId The ID of the user.
     * @return A list of the user's treatments.
     */
    List<TreatmentDto> findAllTreatmentsByUserId(Long userId);

    /**
     * Finds all ongoing treatment records for a specific user.
     * @param userId The ID of the user.
     * @return A list of the user's active treatments.
     */
    List<TreatmentDto> findOngoingTreatmentsByUserId(Long userId);

    /**
     * Finds treatments for a user within a specific date range.
     * @param userId The user's ID.
     * @param startDate The start of the date range.
     * @param endDate The end of the date range.
     * @return A list of treatments within the given period.
     */
    List<TreatmentDto> findTreatmentsByUserIdAndDateRange(Long userId, LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Deletes a treatment record.
     * @param treatmentId The ID of the treatment to delete.
     */
    void deleteTreatment(Long treatmentId);
}