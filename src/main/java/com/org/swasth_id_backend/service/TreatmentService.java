package com.org.swasth_id_backend.service;

import com.org.swasth_id_backend.dto.TreatmentDto;
import com.org.swasth_id_backend.entity.Treatment;
import java.util.List;

public interface TreatmentService {
    Treatment createTreatment(TreatmentDto treatmentDto);
    Treatment getTreatmentById(Long id);
    List<Treatment> getAllTreatments();
    Treatment updateTreatment(Long id, TreatmentDto treatmentDto);
    void deleteTreatment(Long id);
}
