package com.org.swasth_id_backend.service.serviceImpl;

import com.org.swasth_id_backend.dto.TreatmentCreationDto;
import com.org.swasth_id_backend.dto.TreatmentDto;
import com.org.swasth_id_backend.dto.TreatmentUpdateDto;
import com.org.swasth_id_backend.entity.Treatment;
import com.org.swasth_id_backend.entity.User;
import com.org.swasth_id_backend.exception.ResourceNotFoundException;
import com.org.swasth_id_backend.repo.TreatmentRepository;
import com.org.swasth_id_backend.repo.UserRepository;
import com.org.swasth_id_backend.service.TreatmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // Lombok annotation for constructor injection
@Transactional
public class TreatmentServiceImpl implements TreatmentService {

    private final TreatmentRepository treatmentRepository;
    private final UserRepository userRepository;

    @Override
    public TreatmentDto addTreatmentToUser(Long userId, TreatmentCreationDto creationDto) {
        // Find the user who will be associated with this treatment
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        // Let the DTO handle the conversion to an entity
        Treatment newTreatment = creationDto.toEntity(user);

        // Save the new treatment entity
        Treatment savedTreatment = treatmentRepository.save(newTreatment);

        // Convert the saved entity back to a DTO for the response
        return TreatmentDto.fromEntity(savedTreatment);
    }

    @Override
    public TreatmentDto updateTreatment(Long treatmentId, TreatmentUpdateDto updateDto) {
        Treatment existingTreatment = treatmentRepository.findById(treatmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Treatment not found with id: " + treatmentId));

        // Perform a partial update - only change fields that are not null in the DTO
        if (updateDto.getDiagnosis() != null) {
            existingTreatment.setDiagnosis(updateDto.getDiagnosis());
        }
        if (updateDto.getPrescribedMedicines() != null) {
            existingTreatment.setPrescribedMedicines(updateDto.getPrescribedMedicines());
        }
        if (updateDto.getRecommendedTests() != null) {
            existingTreatment.setRecommendedTests(updateDto.getRecommendedTests());
        }
        if (updateDto.getNotes() != null) {
            existingTreatment.setNotes(updateDto.getNotes());
        }
        if (updateDto.getIsOngoing() != null) {
            existingTreatment.setOngoing(updateDto.getIsOngoing());
        }
        if (updateDto.getFollowUpRequired() != null) {
            existingTreatment.setFollowUpRequired(updateDto.getFollowUpRequired());
        }
        if (updateDto.getFollowUpDate() != null) {
            existingTreatment.setFollowUpDate(updateDto.getFollowUpDate());
        }
        if (updateDto.getSeverity() != null) {
            existingTreatment.setSeverity(updateDto.getSeverity());
        }

        Treatment updatedTreatment = treatmentRepository.save(existingTreatment);
        return TreatmentDto.fromEntity(updatedTreatment);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TreatmentDto> findTreatmentById(Long treatmentId) {
        return treatmentRepository.findById(treatmentId)
                .map(TreatmentDto::fromEntity); // Uses a method reference to the static fromEntity method
    }

    @Override
    @Transactional(readOnly = true)
    public List<TreatmentDto> findAllTreatmentsByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }

        return treatmentRepository.findByUserId(userId).stream()
                .map(TreatmentDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TreatmentDto> findOngoingTreatmentsByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }

        return treatmentRepository.findByUserId(userId).stream()
                .filter(Treatment::isOngoing) // Filter for ongoing treatments
                .map(TreatmentDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTreatment(Long treatmentId) {
        if (!treatmentRepository.existsById(treatmentId)) {
            throw new ResourceNotFoundException("Treatment not found with id: " + treatmentId);
        }
        treatmentRepository.deleteById(treatmentId);
    }

    // Stub for the date range method as it can have more complex logic
    @Override
    public List<TreatmentDto> findTreatmentsByUserIdAndDateRange(Long userId, LocalDateTime startDate, LocalDateTime endDate) {
        // You would typically add a custom query to your TreatmentRepository for this
        // For example: List<Treatment> findByUserIdAndDateOfTreatmentBetween(Long userId, LocalDateTime start, LocalDateTime end);
        throw new UnsupportedOperationException("Date range query not yet implemented.");
    }
}