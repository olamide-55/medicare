package com.medicare.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.medicare.model.Patient;
import com.medicare.service.PatientService;

@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/api/signup")
    public Map<String, Object> apiSignup(@RequestBody Map<String, String> data) {
        try {
            Patient patient = new Patient(
                data.get("fullName"),
                data.get("email"),
                data.get("password"),
                data.get("phone")
            );
            patientService.registerPatient(patient);
            return Map.of("success", true, "message", "Account created successfully!");
        } catch (Exception e) {
            return Map.of("success", false, "message", e.getMessage());
        }
    }

    @PostMapping("/api/login")
    public Map<String, Object> apiLogin(@RequestBody Map<String, String> data) {
        var result = patientService.login(data.get("email"), data.get("password"));
        if (result.isPresent()) {
            return Map.of("success", true, "message", "Login successful!", "name", result.get().getFullName());
        }
        return Map.of("success", false, "message", "Invalid email or password");
    }
}