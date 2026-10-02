package com.medicare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicare.model.Doctor;
import com.medicare.repository.DoctorRepository;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor registerDoctor(Doctor doctor) {
        Optional<Doctor> existing = doctorRepository.findByEmail(doctor.getEmail());
        if (existing.isPresent()) {
            throw new RuntimeException("Email already registered");
        }
        return doctorRepository.save(doctor);
    }

    public Optional<Doctor> login(String email, String password) {
        Optional<Doctor> doctor = doctorRepository.findByEmail(email);
        if (doctor.isPresent() && doctor.get().getPassword().equals(password)) {
            return doctor;
        }
        return Optional.empty();
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
}