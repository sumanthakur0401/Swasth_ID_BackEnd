package com.org.swasth_id_backend.mapper;

import com.org.swasth_id_backend.dto.PatientDto;
import com.org.swasth_id_backend.entity.Consultation;
import com.org.swasth_id_backend.entity.DoctorPatient;
import com.org.swasth_id_backend.entity.Patient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class PatientMapper {

    public PatientDto toDto(Patient patient) {
        if (patient == null) {
            return null;
        }


        return PatientDto.builder()
                .id(patient.getId())
                .patientCode(patient.getPatientCode())
                .gender(patient.getGender())
                .knownAllergies(patient.getKnownAllergies())
                .existingConditions(patient.getExistingConditions())
                .emergencyContactName(patient.getEmergencyContactName())
                .emergencyContactNumber(patient.getEmergencyContactNumber())
                .insuranceProvider(patient.getInsuranceProvider())
                .insurancePolicyNumber(patient.getInsurancePolicyNumber())
                .bloodGroup(patient.getBloodGroup())
                .userId(patient.getUser()!= null?patient.getUser().getId():null)
                .consultationIds(patient.getConsultations() != null ?
                        patient.getConsultations().stream().map(Consultation::getId).collect(Collectors.toList()) :
                        List.of())
                .doctorPatientIds(patient.getDoctorPatients() != null ?
                        patient.getDoctorPatients().stream().map(DoctorPatient::getId).collect(Collectors.toList()) :
                        List.of())
                .build();
    }

    public Patient toEntity(PatientDto dto) {
        if (dto == null) {
            return null;
        }

        Patient patient = new Patient();

        patient.setId(dto.getId());
        patient.setPatientCode(dto.getPatientCode());
        patient.setGender(dto.getGender());
        patient.setKnownAllergies(dto.getKnownAllergies());
        patient.setExistingConditions(dto.getExistingConditions());
        patient.setEmergencyContactName(dto.getEmergencyContactName());
        patient.setEmergencyContactNumber(dto.getEmergencyContactNumber());
        patient.setInsuranceProvider(dto.getInsuranceProvider());
        patient.setInsurancePolicyNumber(dto.getInsurancePolicyNumber());
        patient.setBloodGroup(dto.getBloodGroup());

        return patient;
    }


}
