package com.medicare.controller;

import com.medicare.model.Appointment;
import com.medicare.service.AppointmentService;
import com.medicare.service.DoctorService;
import com.medicare.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/admin")
    public String adminDashboard() {
        // Pull real numbers from the database for the stat cards
        int totalPatients = patientService.getAllPatients().size();
        int totalDoctors = doctorService.getAllDoctors().size();
        List<Appointment> appointments = appointmentService.getAllAppointments();
        int totalAppointments = appointments.size();

        // Build one table row per appointment
        StringBuilder rows = new StringBuilder();
        for (Appointment a : appointments) {
            rows.append("<tr>")
                .append("<td>").append(a.getId()).append("</td>")
                .append("<td>").append(a.getPatient().getFullName()).append("</td>")
                .append("<td>").append(a.getDoctor().getFullName()).append("</td>")
                .append("<td>").append(a.getAppointmentDate()).append(" ").append(a.getAppointmentTime()).append("</td>")
                .append("<td><span class='status-tag'>").append(a.getStatus()).append("</span></td>")
                .append("</tr>");
        }

        // If there are no appointments yet, show a friendly message instead of an empty table
        if (appointments.isEmpty()) {
            rows.append("<tr><td colspan='5' style='text-align:center; color:#999;'>No appointments yet</td></tr>");
        }

        return """
                <!DOCTYPE html>
                <html>
                <head>
                    <title>Admin Dashboard - MediCare Hub</title>
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <style>
                        * { margin: 0; padding: 0; box-sizing: border-box; }
                        body { font-family: 'Segoe UI', Arial, sans-serif; background: #eef1f0; display: flex; }
                        .sidebar { width: 80px; background: #1a4d3e; height: 100vh; display: flex; flex-direction: column; align-items: center; padding-top: 25px; gap: 30px; position: fixed; }
                        .sidebar a { color: #cfe3da; text-decoration: none; font-size: 20px; }
                        .main { margin-left: 80px; padding: 30px 40px; width: 100%%; }
                        .topbar { display: flex; justify-content: space-between; align-items: center; margin-bottom: 25px; }
                        .topbar h1 { font-size: 22px; color: #2c3e50; }
                        .stats-row { display: flex; gap: 20px; margin-bottom: 25px; flex-wrap: wrap; }
                        .stat-card { flex: 1; min-width: 180px; background: #fff; border-radius: 12px; padding: 20px; box-shadow: 0 2px 8px rgba(0,0,0,0.05); text-align: center; }
                        .stat-card .number { font-size: 30px; font-weight: bold; color: #1a4d3e; }
                        .stat-card .label { color: #888; font-size: 13px; margin-top: 5px; }
                        .card { background: #fff; border-radius: 12px; padding: 20px; box-shadow: 0 2px 8px rgba(0,0,0,0.05); }
                        .card h3 { margin-bottom: 15px; }
                        table { width: 100%%; border-collapse: collapse; font-size: 14px; }
                        th { text-align: left; color: #888; font-weight: 600; padding: 10px; border-bottom: 2px solid #eee; font-size: 13px; }
                        td { padding: 10px; border-bottom: 1px solid #f0f0f0; }
                        .status-tag { background: #fdf0d5; color: #c9972c; padding: 4px 12px; border-radius: 20px; font-size: 12px; font-weight: 600; }
                    </style>
                </head>
                <body>
                    <div class="sidebar">
                        <a href="/admin">📊</a>
                        <a href="#">👨‍⚕️</a>
                        <a href="#">🧑‍🤝‍🧑</a>
                        <a href="/">🏠</a>
                    </div>
                    <div class="main">
                        <div class="topbar">
                            <h1>Admin Dashboard</h1>
                            <div>🛡️ Admin</div>
                        </div>

                        <div class="stats-row">
                            <div class="stat-card">
                                <div class="number">%d</div>
                                <div class="label">Total Patients</div>
                            </div>
                            <div class="stat-card">
                                <div class="number">%d</div>
                                <div class="label">Total Doctors</div>
                            </div>
                            <div class="stat-card">
                                <div class="number">%d</div>
                                <div class="label">Total Appointments</div>
                            </div>
                        </div>

                        <div class="card">
                            <h3>Recent Appointments</h3>
                            <table>
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Patient</th>
                                        <th>Doctor</th>
                                        <th>Date & Time</th>
                                        <th>Status</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    %s
                                </tbody>
                            </table>
                        </div>
                    </div>
                </body>
                </html>
                """.formatted(totalPatients, totalDoctors, totalAppointments, rows.toString());
    }
}