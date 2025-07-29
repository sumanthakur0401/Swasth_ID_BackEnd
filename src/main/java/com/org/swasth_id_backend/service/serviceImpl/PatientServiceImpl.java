package com.org.swasth_id_backend.service.serviceImpl;

import com.org.swasth_id_backend.dto.PatientDto;
import com.org.swasth_id_backend.dto.UserPatientDto;
import com.org.swasth_id_backend.entity.Patient;
import com.org.swasth_id_backend.entity.User;
import com.org.swasth_id_backend.exception.ResourceAlreadyExistsException;
import com.org.swasth_id_backend.exception.ResourceNotFoundException;
import com.org.swasth_id_backend.exception.UserAlreadyExistsException;
import com.org.swasth_id_backend.mapper.PatientMapper;
import com.org.swasth_id_backend.mapper.UserMapper;
import com.org.swasth_id_backend.repo.PatientRepo;
import com.org.swasth_id_backend.repo.UserRepo;
import com.org.swasth_id_backend.service.PatientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepo patientRepository;
    private final UserRepo userRepository;
    private final UserDetailsServiceImpl userDetailsService;

    @Transactional
    @Override
    public UserPatientDto createPatient(UserPatientDto userPatientDto) throws ResourceNotFoundException, UserAlreadyExistsException {
        Optional<User> user = userRepository.findByEmail(userPatientDto.getUserDto().getEmail());
        if(user.isPresent()) throw new UserAlreadyExistsException("User with this email "+ userPatientDto.getUserDto().getEmail() +" already exists");
        user = userRepository.findByUsername(userPatientDto.getUserDto().getUsername());
        if(user.isPresent()) throw new UserAlreadyExistsException("User with this username "+ userPatientDto.getUserDto().getUsername() +" already exists");
        User newUser = UserMapper.userDtoToUser(userPatientDto.getUserDto());
        newUser.setAge(calculateAge(LocalDate.parse(userPatientDto.getUserDto().getDob())));
        newUser = userDetailsService.addUserByRole(newUser, "ROLE_PATIENT");
        Patient patient = PatientMapper.toEntity(userPatientDto.getPatientDto());
        patient.setUser(newUser);
        patient = patientRepository.save(patient);
        userPatientDto.setUserDto(UserMapper.userToUserDto(newUser));
        userPatientDto.setPatientDto(PatientMapper.toDto(patient));
        return userPatientDto;
    }

    private Integer calculateAgeBeforeSaving(LocalDate dob) {
        if (dob != null) {
             return calculateAge(dob);
        }
        return null;
    }

    private int calculateAge(LocalDate dob) {
        return Period.between(dob, LocalDate.now()).getYears();
    }

    @Override
    public PatientDto getPatientById(UUID id) throws ResourceNotFoundException {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID" + id));
        return PatientMapper.toDto(patient);
    }

    @Override
    public PatientDto getPatientByUserId(UUID userId) throws ResourceNotFoundException {
        Patient patient = patientRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("No Patient found with this user ID" + userId));
        return PatientMapper.toDto(patient);
    }

    @Override
    public List<PatientDto> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(PatientMapper::toDto)
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

        return PatientMapper.toDto(patientRepository.save(patient));
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
                .map(PatientMapper::toDto)
                .collect(Collectors.toList());
    }
}