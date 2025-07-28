package com.org.swasth_id_backend.service.serviceImpl;

import com.org.swasth_id_backend.dto.PatientDto;
import com.org.swasth_id_backend.entity.Patient;
import com.org.swasth_id_backend.entity.User;
import com.org.swasth_id_backend.exception.ResourceAlreadyExistsException;
import com.org.swasth_id_backend.exception.ResourceNotFoundException;
import com.org.swasth_id_backend.mapper.PatientMapper;
import com.org.swasth_id_backend.repo.PatientRepo;
import com.org.swasth_id_backend.repo.UserRepo;
import com.org.swasth_id_backend.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepo patientRepository;
    private final UserRepo userRepository;
    private final PatientMapper patientMapper;


    @Override
    public PatientDto createPatient(PatientDto patientDto) throws ResourceAlreadyExistsException, ResourceNotFoundException {

        if (patientRepository.findByUserId(patientDto.getUserId()).isPresent()) {
            throw new ResourceAlreadyExistsException("Patient already exists for user ID " + patientDto.getUserId());
        }

        Patient patient = patientMapper.toEntity(patientDto);
        LocalDateTime now = LocalDateTime.now();
        patient.setCreatedAt(now);
        patient.setLastUpdate(now);
        if (patientDto.getUserId() != null) {
            User user = userRepository.findById(patientDto.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + patientDto.getUserId()));
            patient.setUser(user);
        }

        return patientMapper.toDto(patientRepository.save(patient));
    }

    @Override
    public PatientDto getPatientById(UUID id) throws ResourceNotFoundException {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID" + id));
        return patientMapper.toDto(patient);
    }

    @Override
    public PatientDto getPatientByUserId(UUID userId) throws ResourceNotFoundException {
        Patient patient = patientRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("No Patient found with this user ID" + userId));
        return patientMapper.toDto(patient);
    }

    @Override
    public List<PatientDto> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(patientMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PatientDto updatePatient(UUID id, PatientDto patientDto) throws ResourceNotFoundException {
        // Fetch existing patient by ID
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID " + id));

        // Validate that userId is not linked to a different patient
        patientRepository.findByUserId(patientDto.getUserId()).ifPresent(existing -> {
            if (!existing.getId().equals(id)) {
                throw new RuntimeException(new ResourceAlreadyExistsException(
                        "Another patient is already linked to user ID " + patientDto.getUserId()));
            }
        });

        // Prevent changes to patientCode
        if (!patient.getPatientCode().equals(patientDto.getPatientCode())) {
            throw new RuntimeException("Patient code cannot be changed.");
        }

        // Allowed updates
        patient.setGender(patientDto.getGender());
        patient.setKnownAllergies(patientDto.getKnownAllergies());
        patient.setExistingConditions(patientDto.getExistingConditions());
        patient.setEmergencyContactName(patientDto.getEmergencyContactName());
        patient.setEmergencyContactNumber(patientDto.getEmergencyContactNumber());
        patient.setInsuranceProvider(patientDto.getInsuranceProvider());
        patient.setInsurancePolicyNumber(patientDto.getInsurancePolicyNumber());
        patient.setBloodGroup(patientDto.getBloodGroup());
        patient.setLastUpdate(LocalDateTime.now());

        // Set user if userId present
        if (patientDto.getUserId() != null) {
            User user = new User();
            user.setId(patientDto.getUserId());
            patient.setUser(user);
        }

        return patientMapper.toDto(patientRepository.save(patient));
    }


    @Override
    public void deletePatient(UUID id) throws ResourceNotFoundException {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID" + id));
        patientRepository.delete(patient);
    }

    @Override
    public List<PatientDto> getPatientsByBloodGroup(String bloodGroup) {
        return patientRepository.findByBloodGroupIgnoreCase(bloodGroup)
                .stream()
                .map(patientMapper::toDto)
                .collect(Collectors.toList());
    }
}