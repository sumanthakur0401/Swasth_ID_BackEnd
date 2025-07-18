package com.org.swasth_id_backend.service.serviceImpl;

import com.org.swasth_id_backend.dto.MedicalHistoryDto;
import com.org.swasth_id_backend.entity.MedicalHistory;
import com.org.swasth_id_backend.entity.User;
import com.org.swasth_id_backend.exception.ResourceNotFoundException;
import com.org.swasth_id_backend.repo.MedicalHistoryRepository;
import com.org.swasth_id_backend.repo.UserRepository;
import com.org.swasth_id_backend.service.MedicalHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicalHistoryServiceImpl implements MedicalHistoryService {

    private final MedicalHistoryRepository historyRepository;
    private final UserRepository userRepository;

    @Override
    public MedicalHistoryDto getHistoryById(Long historyId) {
        MedicalHistory history = historyRepository.findById(historyId)
                .orElseThrow(() -> new ResourceNotFoundException("Medical history not found"));
        return MedicalHistoryDto.fromEntity(history);
    }

    @Override
    public List<MedicalHistoryDto> getHistoryByUserId(Long userId) {
        return historyRepository.findByUserId(userId).stream()
                .map(MedicalHistoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public MedicalHistoryDto createHistory(MedicalHistoryDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        MedicalHistory entity = MedicalHistoryDto.toEntity(dto);
        entity.setUser(user);

        MedicalHistory saved = historyRepository.save(entity);
        return MedicalHistoryDto.fromEntity(saved);
    }
}