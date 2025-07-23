//package com.org.swasth_id_backend.controller;
//
//import com.org.swasth_id_backend.dto.MedicalHistoryDto;
//import com.org.swasth_id_backend.service.MedicalHistoryService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.*;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/medical-history")
//@RequiredArgsConstructor
//@Tag(name = "Medical History", description = "Endpoints for archived treatments")
//public class MedicalHistoryController {
//
//    private final MedicalHistoryService historyService;
//
//    @GetMapping("/{id}")
//    @Operation(summary = "Get history by history ID")
//    public MedicalHistoryDto getHistoryById(@PathVariable Long id) {
//        return historyService.getHistoryById(id);
//    }
//
//    @GetMapping("/user/{userId}")
//    @Operation(summary = "Get history by user ID")
//    public List<MedicalHistoryDto> getHistoryByUser(@PathVariable Long userId) {
//        return historyService.getHistoryByUserId(userId);
//    }
//
//    @PostMapping
//    @Operation(summary = "Create a new medical history record")
//    public ResponseEntity<MedicalHistoryDto> createHistory(@Valid @RequestBody MedicalHistoryDto dto) {
//        MedicalHistoryDto created = historyService.createHistory(dto);
//        return ResponseEntity.status(HttpStatus.CREATED).body(created);
//    }
//}
