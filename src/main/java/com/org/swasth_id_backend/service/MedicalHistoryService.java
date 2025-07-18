package com.org.swasth_id_backend.service;

import com.org.swasth_id_backend.dto.MedicalHistoryDto;
import java.util.List;

public interface MedicalHistoryService {
    MedicalHistoryDto getHistoryById(Long historyId);
    List<MedicalHistoryDto> getHistoryByUserId(Long userId);
    MedicalHistoryDto createHistory(MedicalHistoryDto dto);
}
