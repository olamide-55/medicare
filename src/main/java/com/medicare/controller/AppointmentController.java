package com.medicare.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.medicare.model.Doctor;
import com.medicare.service.AppointmentService;
import com.medicare.service.DoctorService;

@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;

    // ---------- BOOKING PAGE ----------
    @GetMapping("/book")
    public String bookingPage() {
        List<Doctor> doctors = doctorService.getAllDoctors();

        // Builds the <option> tags for the doctor dropdown by looping over every
        // doctor saved in the database. %s in the HTML below gets replaced with this.
        StringBuilder doctorOptions = new StringBuilder();
        for (Doctor d : doctors) {
            doctorOptions.append("<option value='").append(d.getId()).append("'>")
                    .append(d.getFullName()).append(" - ").append(d.getSpecialization())
                    .append("</option>");
        }

        return """
                <!DOCTYPE html>
                <html>
                <head>
                    <title>Book Appointment - MediCare Hub</title>
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <style>
                        * { margin: 0; padding: 0; box-sizing: border-box; }
                        body { font-family: 'Segoe UI', Arial, sans-serif; background: #f5f5f2; display: flex; align-items: center; justify-content: center; min-height: 100vh; }
                        .card { background: #fff; padding: 40px; border-radius: 12px; box-shadow: 0 4px 20px rgba(0,0,0,0.08); width: 420px; }
                        .card h2 { margin-bottom: 20px; color: #1a3c34; }
                        label { display: block; font-size: 13px; margin-bottom: 5px; color: #555; margin-top: 12px; }
                        input, select { width: 100%%; padding: 10px 12px; border: 1px solid #ddd; border-radius: 6px; font-size: 14px; }
                        .submit-btn { width: 100%%; background: #e08a3e; color: white; border: none; padding: 12px; border-radius: 6px; font-weight: 600; margin-top: 20px; cursor: pointer; }
                    </style>
                </head>
                <body>
                    <div class="card">
                        <h2>Book an Appointment</h2>

                        <!-- This replaces the old "type your Patient ID" input box.
                             JavaScript fills this in automatically with the logged-in
                             patient's name once the page loads. -->
                        <p id="patientGreeting" style="margin-bottom:10px; color:#555;"></p>

                        <form id="bookingForm" onsubmit="return submitBooking(event)">
                            <label>Choose Doctor</label>
                            <select id="doctorId" required>
                                <option value="">-- Select a doctor --</option>
                                %s
                            </select>

                            <label>Date</label>
                            <input type="date" id="appointmentDate" required>

                            <label>Time</label>
                            <input type="time" id="appointmentTime" required>

                            <label>Reason for Visit</label>
                            <input type="text" id="reason" placeholder="e.g. Routine checkup">

                            <button type="submit" class="submit-btn">Confirm Booking</button>
                        </form>
                    </div>
                    <script>
                        // Runs automatically the moment this page finishes loading
                        window.onload = function() {
                            // Read back the ID and name we saved during login
                            const patientId = sessionStorage.getItem('patientId');
                            const patientName = sessionStorage.getItem('patientName');

                            // If nothing was saved, nobody is logged in, so send them to /login
                            if (!patientId) {
                                alert('Please log in first');
                                window.location.href = '/login';
                                return;
                            }

                            // Show a friendly "Booking as: Name" message on the page
                            document.getElementById('patientGreeting').innerText = 'Booking as: ' + patientName;
                        };

                        function submitBooking(event) {
                            event.preventDefault();

                            fetch('/api/book', {
                                method: 'POST',
                                headers: { 'Content-Type': 'application/json' },
                                body: JSON.stringify({
                                    // Pulled from sessionStorage instead of a text box now,
                                    // so the user never has to know or type their own ID
                                    patientId: sessionStorage.getItem('patientId'),
                                    doctorId: document.getElementById('doctorId').value,
                                    date: document.getElementById('appointmentDate').value,
                                    time: document.getElementById('appointmentTime').value,
                                    reason: document.getElementById('reason').value
                                })
                            })
                            .then(response => response.json())
                            .then(result => {
                                alert(result.message);
                                if (result.success) window.location.href = '/patients';
                            })
                            .catch(error => alert('Error: ' + error));

                            return false;
                        }
                    </script>
                </body>
                </html>
                """.formatted(doctorOptions.toString());
    }

    // ---------- BOOKING API ----------
    // This part is unchanged — it still receives patientId, doctorId, date, time,
    // reason, and saves the appointment. It doesn't care whether the ID came from
    // a text box or sessionStorage, since both arrive the same way: as JSON.
    @PostMapping("/api/book")
    public Map<String, Object> apiBook(@RequestBody Map<String, String> data) {
        try {
            Long patientId = Long.parseLong(data.get("patientId"));
            Long doctorId = Long.parseLong(data.get("doctorId"));

            appointmentService.bookAppointment(
                patientId, doctorId,
                data.get("date"), data.get("time"), data.get("reason")
            );
            return Map.of("success", true, "message", "Appointment booked successfully!");
        } catch (Exception e) {
            return Map.of("success", false, "message", "Error: " + e.getMessage());
        }
    }
}