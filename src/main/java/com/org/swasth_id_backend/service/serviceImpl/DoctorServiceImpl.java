package com.org.swasth_id_backend.service.serviceImpl;

import com.org.swasth_id_backend.dto.DoctorDto;
import com.org.swasth_id_backend.entity.Doctor;
import com.org.swasth_id_backend.entity.User;
import com.org.swasth_id_backend.exception.ResourceAlreadyExistsException;
import com.org.swasth_id_backend.exception.ResourceNotFoundException;
import com.org.swasth_id_backend.mapper.DoctorMapper;
import com.org.swasth_id_backend.repo.DoctorRepo;
import com.org.swasth_id_backend.repo.UserRepo;
import com.org.swasth_id_backend.service.DoctorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepo doctorRepo;
    private final UserRepo userRepo;

    @Override
    public DoctorDto createDoctor(DoctorDto doctorDto) throws ResourceAlreadyExistsException, ResourceNotFoundException {
        boolean licenseExists = doctorRepo.existsByLicenseNumber(doctorDto.getLicenseNumber());
        boolean phoneExists = doctorRepo.existsByPhoneNumber(doctorDto.getPhoneNumber());

        if (licenseExists || phoneExists) {
            throw new ResourceAlreadyExistsException("Doctor with this license number or phone number already exists.");
        }


        Doctor doctor = DoctorMapper.toEntity(doctorDto);
        LocalDateTime now = LocalDateTime.now();
        doctor.setCreatedAt(now);
        doctor.setLastUpdate(now);
        if (doctorDto.getUserId() != null) {
            User user = userRepo.findById(doctorDto.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + doctorDto.getUserId()));
            doctor.setUser(user);
        }
        Doctor saved = doctorRepo.save(doctor);
        System.out.println(saved);
        return DoctorMapper.toDto(saved);
    }


    @Override
    public DoctorDto getDoctorById(UUID id) throws ResourceNotFoundException {
        Doctor doctor = doctorRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
        return DoctorMapper.toDto(doctor);
    }

    @Override
    public List<DoctorDto> getAllDoctors() {
        return doctorRepo.findAll().stream()
                .map(DoctorMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DoctorDto updateDoctor(UUID id, DoctorDto doctorDto) throws ResourceNotFoundException {
        Doctor existing = doctorRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));

        // Only update mutable fields (not id)
        existing.setSpecialization(doctorDto.getSpecialization());
        existing.setPhoneNumber(doctorDto.getPhoneNumber());
        existing.setLicenseNumber(doctorDto.getLicenseNumber());
        existing.setYearsOfExperience(doctorDto.getYearsOfExperience());
        existing.setHospitalName(doctorDto.getHospitalName());
        existing.setAvailableTimings(doctorDto.getAvailableTimings());
        existing.setLastUpdate(LocalDateTime.now());

        if (doctorDto.getUserId() != null) {
            User user = new User();
            user.setId(doctorDto.getUserId());
            existing.setUser(user);
        }

        Doctor updated = doctorRepo.save(existing);
        return DoctorMapper.toDto(updated);
    }

    @Override
    public void deleteDoctor(UUID id) throws ResourceNotFoundException {
        if (!doctorRepo.existsById(id)) {
            throw new ResourceNotFoundException("Doctor not found with id: " + id);
        }
        doctorRepo.deleteById(id);
    }

    @Override
    public List<DoctorDto> searchDoctors(String specialization, String hospital) {
        return doctorRepo.findAll().stream()
                .filter(doc -> (specialization == null || doc.getSpecialization().equalsIgnoreCase(specialization)) &&
                        (hospital == null || doc.getHospitalName().equalsIgnoreCase(hospital)))
                .map(DoctorMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DoctorDto getDoctorByUserId(UUID userId) throws ResourceNotFoundException {
        return doctorRepo.findAll().stream()
                .filter(doc -> doc.getUser() != null && doc.getUser().getId().equals(userId))
                .findFirst()
                .map(DoctorMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found for userId: " + userId));
    }
}
