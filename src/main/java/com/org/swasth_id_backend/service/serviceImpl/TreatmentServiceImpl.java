package com.org.swasth_id_backend.service.serviceImpl;

import com.org.swasth_id_backend.dto.TreatmentDto;
import com.org.swasth_id_backend.entity.Treatment;
import com.org.swasth_id_backend.repo.TreatmentRepository;
import com.org.swasth_id_backend.service.TreatmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TreatmentServiceImpl implements TreatmentService {

    private final TreatmentRepository repository;

    @Override
    public Treatment createTreatment(TreatmentDto dto) {
        // Ensure followUpDate is null if follow-up not required
        if (!dto.isFollowUpRequired()) {
            dto.setFollowUpDate(null);
        }

        Treatment treatment = Treatment.builder()
                .diagnosis(dto.getDiagnosis())
                .prescribedMedicines(dto.getPrescribedMedicines())
                .recommendedTests(dto.getRecommendedTests())
                .notes(dto.getNotes())
                .dateOfTreatment(dto.getDateOfTreatment())
                .isOngoing(dto.isOngoing())
                .followUpRequired(dto.isFollowUpRequired())
                .followUpDate(dto.getFollowUpDate())
                .severity(dto.getSeverity())
                .build();

        return repository.save(treatment);
    }

    @Override
    public Treatment getTreatmentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Treatment not found with ID: " + id));
    }

    @Override
    public List<Treatment> getAllTreatments() {
        return repository.findAll();
    }

    @Override
    public Treatment updateTreatment(Long id, TreatmentDto dto) {
        // Ensure followUpDate is null if follow-up not required
        if (!dto.isFollowUpRequired()) {
            dto.setFollowUpDate(null);
        }

        Treatment existing = getTreatmentById(id);

        existing.setDiagnosis(dto.getDiagnosis());
        existing.setPrescribedMedicines(dto.getPrescribedMedicines());
        existing.setRecommendedTests(dto.getRecommendedTests());
        existing.setNotes(dto.getNotes());
        existing.setDateOfTreatment(dto.getDateOfTreatment());
        existing.setOngoing(dto.isOngoing());
        existing.setFollowUpRequired(dto.isFollowUpRequired());
        existing.setFollowUpDate(dto.getFollowUpDate());
        existing.setSeverity(dto.getSeverity());

        return repository.save(existing);
    }

    @Override
    public void deleteTreatment(Long id) {
        repository.deleteById(id);
    }
}
