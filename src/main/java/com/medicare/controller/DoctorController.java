package com.medicare.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.medicare.model.Doctor;
import com.medicare.service.DoctorService;

@RestController
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/doctors")
    public String doctorDashboard() {
        return """
                <!DOCTYPE html>
                <html>
                <head>
                    <title>Doctor Dashboard - MediCare Hub</title>
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <style>
                        * { margin: 0; padding: 0; box-sizing: border-box; }
                        body { font-family: 'Segoe UI', Arial, sans-serif; background: #eef1f0; display: flex; }
                        .sidebar { width: 80px; background: #1a4d3e; height: 100vh; display: flex; flex-direction: column; align-items: center; padding-top: 25px; gap: 30px; position: fixed; }
                        .sidebar .logo { font-size: 26px; margin-bottom: 20px; }
                        .sidebar a { color: #cfe3da; text-decoration: none; font-size: 20px; }
                        .main { margin-left: 80px; padding: 30px 40px; width: 100%; }
                        .topbar { display: flex; justify-content: space-between; align-items: center; margin-bottom: 25px; }
                        .topbar h1 { font-size: 22px; color: #2c3e50; }
                        .card { background: #fff; border-radius: 12px; padding: 20px; box-shadow: 0 2px 8px rgba(0,0,0,0.05); margin-bottom: 20px; }
                        .appointment-row { display: flex; justify-content: space-between; align-items: center; padding: 12px 0; border-bottom: 1px solid #eee; }
                        .appointment-row:last-child { border-bottom: none; }
                        .status-tag { padding: 4px 12px; border-radius: 20px; font-size: 12px; font-weight: 600; }
                        .status-pending { background: #fdf0d5; color: #c9972c; }
                        .status-confirmed { background: #d8f0e4; color: #1a7a4d; }
                        .btn { background: #1a4d3e; color: white; border: none; padding: 8px 16px; border-radius: 6px; cursor: pointer; font-size: 13px; }
                    </style>
                </head>
                <body>
                    <div class="sidebar">
                        <div class="logo">🏥</div>
                        <a href="/doctors">📊</a>
                        <a href="#">👥</a>
                        <a href="#">📅</a>
                        <a href="/">🏠</a>
                    </div>
                    <div class="main">
                        <div class="topbar">
                            <h1>Doctor Dashboard</h1>
                            <div>👨‍⚕️ Dr. Alex Hess</div>
                        </div>

                        <div class="card">
                            <h3 style="margin-bottom:15px;">Today's Appointments</h3>
                            <div class="appointment-row">
                                <span>Roger Curtis — 9:00 AM</span>
                                <span class="status-tag status-confirmed">Confirmed</span>
                                <button class="btn">Add Diagnosis</button>
                            </div>
                            <div class="appointment-row">
                                <span>Jane Smith — 10:30 AM</span>
                                <span class="status-tag status-pending">Pending</span>
                                <button class="btn">Add Diagnosis</button>
                            </div>
                            <div class="appointment-row">
                                <span>Mark Johnson — 1:00 PM</span>
                                <span class="status-tag status-confirmed">Confirmed</span>
                                <button class="btn">Add Diagnosis</button>
                            </div>
                        </div>

                        <div class="card">
                            <h3 style="margin-bottom:15px;">Weekly Availability</h3>
                            <p style="color:#888; font-size:14px;">Mon–Fri, 9:00 AM – 4:00 PM</p>
                        </div>
                    </div>
                </body>
                </html>
                """;
    }

    @PostMapping("/api/doctor/signup")
    public Map<String, Object> apiDoctorSignup(@RequestBody Map<String, String> data) {
        try {
            Doctor doctor = new Doctor(
                data.get("fullName"),
                data.get("email"),
                data.get("password"),
                data.get("specialization")
            );
            doctorService.registerDoctor(doctor);
            return Map.of("success", true, "message", "Doctor account created!");
        } catch (Exception e) {
            return Map.of("success", false, "message", e.getMessage());
        }
    }

    @PostMapping("/api/doctor/login")
    public Map<String, Object> apiDoctorLogin(@RequestBody Map<String, String> data) {
        var result = doctorService.login(data.get("email"), data.get("password"));
        if (result.isPresent()) {
            return Map.of("success", true, "message", "Login successful!", "name", result.get().getFullName());
        }
        return Map.of("success", false, "message", "Invalid email or password");
    }
}