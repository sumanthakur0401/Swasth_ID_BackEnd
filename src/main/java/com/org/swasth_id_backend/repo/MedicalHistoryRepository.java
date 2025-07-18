package com.org.swasth_id_backend.repo;

import com.org.swasth_id_backend.entity.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Long> {
    List<MedicalHistory> findByUserId(Long userId);
}

