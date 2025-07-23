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
       return null;
    }

    @Override
    public List<MedicalHistoryDto> getHistoryByUserId(Long userId) {
        return null;
    }

    @Override
    public MedicalHistoryDto createHistory(MedicalHistoryDto dto) {
        return null;
    }
}