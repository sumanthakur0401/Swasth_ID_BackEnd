package com.org.swasth_id_backend.controller;

import com.org.swasth_id_backend.dto.TreatmentCreationDto;
import com.org.swasth_id_backend.dto.TreatmentDto;
import com.org.swasth_id_backend.dto.TreatmentUpdateDto;
import com.org.swasth_id_backend.exception.ResourceNotFoundException;
import com.org.swasth_id_backend.service.TreatmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Treatment Controller", description = "Endpoints for managing medical treatments")
@RequiredArgsConstructor
public class TreatmentController {

    private final TreatmentService treatmentService;

    @Operation(summary = "Add a new treatment record for a specific user")
    @PostMapping("/users/{userId}/treatments")
    public ResponseEntity<TreatmentDto> addTreatmentToUser(
            @PathVariable Long userId,
            @Valid @RequestBody TreatmentCreationDto creationDto) {
        TreatmentDto newTreatment = treatmentService.addTreatmentToUser(userId, creationDto);
        return new ResponseEntity<>(newTreatment, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all treatments for a specific user")
    @GetMapping("/users/{userId}/treatments")
    public ResponseEntity<List<TreatmentDto>> getAllTreatmentsForUser(@PathVariable Long userId) {
        List<TreatmentDto> treatments = treatmentService.findAllTreatmentsByUserId(userId);
        return ResponseEntity.ok(treatments);
    }

    @Operation(summary = "Get a single treatment by its ID")
    @GetMapping("/treatments/{treatmentId}")
    public ResponseEntity<TreatmentDto> getTreatmentById(@PathVariable Long treatmentId) {
        return treatmentService.findTreatmentById(treatmentId)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Treatment not found with id: " + treatmentId));
    }

    @Operation(summary = "Update an existing treatment (partial update)")
    @PutMapping("/treatments/{treatmentId}")
    public ResponseEntity<TreatmentDto> updateTreatment(
            @PathVariable Long treatmentId,
            @Valid @RequestBody TreatmentUpdateDto updateDto) {
        TreatmentDto updatedTreatment = treatmentService.updateTreatment(treatmentId, updateDto);
        return ResponseEntity.ok(updatedTreatment);
    }

    @Operation(summary = "Delete a treatment by its ID")
    @DeleteMapping("/treatments/{treatmentId}")
    public ResponseEntity<Void> deleteTreatment(@PathVariable Long treatmentId) {
        treatmentService.deleteTreatment(treatmentId);
        return ResponseEntity.noContent().build();
    }
}