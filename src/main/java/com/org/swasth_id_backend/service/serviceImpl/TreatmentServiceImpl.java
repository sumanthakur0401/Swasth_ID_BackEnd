//package com.org.swasth_id_backend.service.serviceImpl;
//
//import com.org.swasth_id_backend.dto.TreatmentCreationDto;
//import com.org.swasth_id_backend.dto.TreatmentDto;
//import com.org.swasth_id_backend.dto.TreatmentUpdateDto;
//import com.org.swasth_id_backend.entity.MedicalHistory;
//import com.org.swasth_id_backend.entity.Treatment;
//import com.org.swasth_id_backend.entity.User;
//import com.org.swasth_id_backend.exception.ResourceNotFoundException;
//import com.org.swasth_id_backend.repo.MedicalHistoryRepository;
//import com.org.swasth_id_backend.repo.TreatmentRepository;
//import com.org.swasth_id_backend.repo.UserRepository;
//import com.org.swasth_id_backend.service.TreatmentService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class TreatmentServiceImpl implements TreatmentService {
//
//    private final TreatmentRepository treatmentRepository;
//    private final UserRepository userRepository;
//    private final MedicalHistoryRepository medicalHistoryRepository;
//
//    @Override
//    public TreatmentDto addTreatmentToUser(Long userId, TreatmentCreationDto creationDto) {
//        return null;
//    }
//
//    @Override
//    public TreatmentDto updateTreatment(Long treatmentId, TreatmentUpdateDto updateDto) {
//        return null;
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public Optional<TreatmentDto> findTreatmentById(Long treatmentId) {
//        return null;
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<TreatmentDto> findAllTreatmentsByUserId(Long userId) {
//        return null;
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<TreatmentDto> findOngoingTreatmentsByUserId(Long userId) {
//        return null;
//    }
//
//    @Override
//    public void deleteTreatment(Long treatmentId) {
//        
//    }
//
//    @Override
//    public List<TreatmentDto> findTreatmentsByUserIdAndDateRange(Long userId, LocalDateTime startDate, LocalDateTime endDate) {
//        return null;
//    }
//}
