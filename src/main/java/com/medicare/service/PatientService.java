package com.medicare.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicare.model.Patient;
import com.medicare.repository.PatientRepository;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient registerPatient(Patient patient) {
        Optional<Patient> existing = patientRepository.findByEmail(patient.getEmail());
        if (existing.isPresent()) {
            throw new RuntimeException("Email already registered");
        }
        return patientRepository.save(patient);
    }

    public Optional<Patient> login(String email, String password) {
        Optional<Patient> patient = patientRepository.findByEmail(email);
        if (patient.isPresent() && patient.get().getPassword().equals(password)) {
            return patient;
        }
        return Optional.empty();
    }

        public java.util.List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

        public Patient updateProfile(Long patientId, String gender, String bloodType, String allergies, String diseases, String height, String weight) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        patient.setGender(gender);
        patient.setBloodType(bloodType);
        patient.setAllergies(allergies);
        patient.setDiseases(diseases);
        patient.setHeight(height);
        patient.setWeight(weight);

        return patientRepository.save(patient);
    }

    public Patient getPatientById(Long patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }
}