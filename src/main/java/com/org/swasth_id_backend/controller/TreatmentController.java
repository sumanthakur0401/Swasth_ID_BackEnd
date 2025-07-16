package com.org.swasth_id_backend.controller;

import com.org.swasth_id_backend.dto.TreatmentDto;
import com.org.swasth_id_backend.entity.Treatment;
import com.org.swasth_id_backend.service.TreatmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * REST controller for managing Treatment resources.
 */
@Tag(name = "treatment")
@RestController
@RequestMapping("/api/treatments")
@RequiredArgsConstructor
public class TreatmentController {

    private final TreatmentService treatmentService;

    /**
     * Create a new treatment.
     * @param dto Data for the new treatment
     * @return Message confirming creation
     */
    @PostMapping
    public ResponseEntity<String> createTreatment(@RequestBody @Valid TreatmentDto dto) {
        Treatment created = treatmentService.createTreatment(dto);
        String message = "✅ Treatment created successfully with ID: " + created.getTreatmentId();
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    /**
     * Get a treatment by ID.
     * @param id Treatment ID
     * @return Treatment object if found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Treatment> getTreatmentById(@PathVariable Long id) {
        return ResponseEntity.ok(treatmentService.getTreatmentById(id));
    }

    /**
     * List all treatments.
     * @return List of treatments
     */
    @GetMapping
    public ResponseEntity<List<Treatment>> getAllTreatments() {
        return ResponseEntity.ok(treatmentService.getAllTreatments());
    }

    /**
     * Update a treatment by ID.
     * @param id Treatment ID
     * @param dto Updated treatment data
     * @return Message confirming update
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTreatment(
            @PathVariable Long id,
            @RequestBody @Valid TreatmentDto dto) {
        Treatment updated = treatmentService.updateTreatment(id, dto);
        String message = "🛠️ Treatment updated successfully for ID: " + updated.getTreatmentId();
        return ResponseEntity.ok(message);
    }

    /**
     * Delete a treatment by ID.
     * @param id Treatment ID
     * @return No content response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTreatment(@PathVariable Long id) {
        treatmentService.deleteTreatment(id);
        return ResponseEntity.noContent().build();
    }
}
