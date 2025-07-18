package com.org.swasth_id_backend.service.serviceImpl;

import com.org.swasth_id_backend.dto.TreatmentCreationDto;
import com.org.swasth_id_backend.dto.TreatmentDto;
import com.org.swasth_id_backend.dto.TreatmentUpdateDto;
import com.org.swasth_id_backend.entity.MedicalHistory;
import com.org.swasth_id_backend.entity.Treatment;
import com.org.swasth_id_backend.entity.User;
import com.org.swasth_id_backend.exception.ResourceNotFoundException;
import com.org.swasth_id_backend.repo.MedicalHistoryRepository;
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
@RequiredArgsConstructor
@Transactional
public class TreatmentServiceImpl implements TreatmentService {

    private final TreatmentRepository treatmentRepository;
    private final UserRepository userRepository;
    private final MedicalHistoryRepository medicalHistoryRepository;

    @Override
    public TreatmentDto addTreatmentToUser(Long userId, TreatmentCreationDto creationDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Treatment newTreatment = creationDto.toEntity(user);
        Treatment savedTreatment = treatmentRepository.save(newTreatment);

        return TreatmentDto.fromEntity(savedTreatment);
    }

    @Override
    public TreatmentDto updateTreatment(Long treatmentId, TreatmentUpdateDto updateDto) {
        Treatment treatment = treatmentRepository.findById(treatmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Treatment not found with id: " + treatmentId));

        if (updateDto.getDiagnosis() != null) treatment.setDiagnosis(updateDto.getDiagnosis());
        if (updateDto.getPrescribedMedicines() != null) treatment.setPrescribedMedicines(updateDto.getPrescribedMedicines());
        if (updateDto.getRecommendedTests() != null) treatment.setRecommendedTests(updateDto.getRecommendedTests());
        if (updateDto.getNotes() != null) treatment.setNotes(updateDto.getNotes());
        if (updateDto.getFollowUpRequired() != null) treatment.setFollowUpRequired(updateDto.getFollowUpRequired());
        if (updateDto.getFollowUpDate() != null) treatment.setFollowUpDate(updateDto.getFollowUpDate());
        if (updateDto.getSeverity() != null) treatment.setSeverity(updateDto.getSeverity());

        boolean wasOngoing = treatment.isOngoing();

        if (updateDto.getIsOngoing() != null) {

            treatment.setOngoing(updateDto.getIsOngoing());

            if (!updateDto.getIsOngoing() && wasOngoing) {
                MedicalHistory history = MedicalHistory.builder()
                        .user(treatment.getUser())
                        .diagnosis(treatment.getDiagnosis())
                        .prescribedMedicines(treatment.getPrescribedMedicines())
                        .recommendedTests(treatment.getRecommendedTests())
                        .notes(treatment.getNotes())
                        .dateOfTreatment(treatment.getDateOfTreatment())
                        .severity(treatment.getSeverity())
                        .build();

                medicalHistoryRepository.save(history);
                treatmentRepository.delete(treatment);
                return null;
            }
        }

        Treatment updatedTreatment = treatmentRepository.save(treatment);
        return TreatmentDto.fromEntity(updatedTreatment);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TreatmentDto> findTreatmentById(Long treatmentId) {
        return treatmentRepository.findById(treatmentId)
                .map(TreatmentDto::fromEntity);
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
                .filter(Treatment::isOngoing)
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

    @Override
    public List<TreatmentDto> findTreatmentsByUserIdAndDateRange(Long userId, LocalDateTime startDate, LocalDateTime endDate) {
        throw new UnsupportedOperationException("Date range query not yet implemented.");
    }
}
