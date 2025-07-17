package com.org.swasth_id_backend.repo;

import com.org.swasth_id_backend.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List; // Import List

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Long> {

    /**
     * Finds all treatments associated with a specific user ID.
     * @param userId The ID of the user.
     * @return A list of treatments for the given user.
     */
    List<Treatment> findByUserId(Long userId);
}