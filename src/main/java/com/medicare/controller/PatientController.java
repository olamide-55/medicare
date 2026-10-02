package com.medicare.controller;

import com.medicare.model.Patient;
import com.medicare.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

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
            // Added "id" here. The browser needs this number so it can remember
            // which patient is logged in, without asking them to type it in later.
            return Map.of(
                "success", true,
                "message", "Login successful!",
                "name", result.get().getFullName(),
                "id", result.get().getId()
            );
        }
        return Map.of("success", false, "message", "Invalid email or password");
    }

        @PostMapping("/api/profile/update")
    public Map<String, Object> apiUpdateProfile(@RequestBody Map<String, String> data) {
        try {
            Long patientId = Long.parseLong(data.get("patientId"));
            patientService.updateProfile(
                patientId,
                data.get("gender"),
                data.get("bloodType"),
                data.get("allergies"),
                data.get("diseases"),
                data.get("height"),
                data.get("weight")
            );
            return Map.of("success", true, "message", "Profile updated!");
        } catch (Exception e) {
            return Map.of("success", false, "message", e.getMessage());
        }
    }

    @GetMapping("/api/profile/{id}")
    public Patient apiGetProfile(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }
}