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
}