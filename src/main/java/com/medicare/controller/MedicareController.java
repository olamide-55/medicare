package com.medicare.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedicareController {

    @GetMapping("/")
    public String home() {
        return """
                <!DOCTYPE html>
                <html>
                <head>
                    <title>MediCare Hub</title>
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <style>
                        * { margin: 0; padding: 0; box-sizing: border-box; }
                        body { font-family: 'Segoe UI', Arial, sans-serif; background: #f5f5f2; color: #2c3e50; }
                        nav { display: flex; justify-content: space-between; align-items: center; padding: 20px 60px; background: #fff; }
                        nav .logo { font-size: 22px; font-weight: bold; color: #1a5c4a; }
                        nav ul { list-style: none; display: flex; gap: 30px; }
                        nav ul li a { text-decoration: none; color: #2c3e50; font-weight: 500; }
                        nav .cta-btn { background: #e08a3e; color: white; padding: 10px 22px; border-radius: 6px; text-decoration: none; font-weight: 600; }
                        .hero { display: flex; align-items: center; justify-content: space-between; padding: 60px; background: #fff; }
                        .hero-text { max-width: 500px; }
                        .hero-text h1 { font-size: 42px; margin-bottom: 20px; color: #1a3c34; }
                        .hero-text p { font-size: 16px; color: #555; margin-bottom: 25px; }
                        .hero-text a { display: inline-block; background: #e08a3e; color: white; padding: 14px 30px; border-radius: 6px; text-decoration: none; font-weight: 600; }
                        .hero-img { font-size: 100px; }
                        .features { display: flex; gap: 20px; padding: 40px 60px; flex-wrap: wrap; }
                        .feature-card { flex: 1; min-width: 220px; background: #fff; padding: 25px; border-radius: 10px; box-shadow: 0 2px 8px rgba(0,0,0,0.05); }
                        .feature-card .icon { font-size: 30px; margin-bottom: 10px; }
                        .feature-card h3 { margin-bottom: 8px; }
                        .feature-card p { color: #777; font-size: 14px; margin-bottom: 12px; }
                        .feature-card button { background: #eee; border: none; padding: 8px 16px; border-radius: 5px; cursor: pointer; font-weight: 600; }
                        .services { padding: 40px 60px; }
                        .services h2 { margin-bottom: 25px; }
                        .service-grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(200px, 1fr)); gap: 20px; }
                        .service-item { background: #fff; border-radius: 10px; overflow: hidden; box-shadow: 0 2px 8px rgba(0,0,0,0.05); }
                        .service-item .img-placeholder { height: 140px; background: #d8e6df; display: flex; align-items: center; justify-content: center; font-size: 40px; }
                        .service-item .info { padding: 15px; }
                        .service-item .info h4 { margin-bottom: 4px; }
                        .service-item .info p { font-size: 13px; color: #888; }
                        footer { background: #2c3e30; color: white; padding: 50px 60px; display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 20px; }
                        footer h2 { max-width: 400px; }
                        footer a { background: #e08a3e; color: white; padding: 14px 28px; border-radius: 6px; text-decoration: none; font-weight: 600; }
                    </style>
                </head>
                <body>
                    <nav>
                        <div class="logo">MediCare Hub</div>
                        <ul>
                            <li><a href="/">Home</a></li>
                            <li><a href="/patients">Patient Dashboard</a></li>
                            <li><a href="/login">Login</a></li>
                            <li><a href="/signup">Sign Up</a></li>
                        </ul>
                        <a class="cta-btn" href="/signup">Book Appointment</a>
                    </nav>
                    <section class="hero">
                        <div class="hero-text">
                            <h1>Your Health, Managed With Confidence</h1>
                            <p>Schedule appointments, message your doctor, and access your medical records — all in one secure hub.</p>
                            <a href="/signup">Get Started</a>
                        </div>
                        <div class="hero-img">🏥</div>
                    </section>
                    <section class="features">
                        <div class="feature-card"><div class="icon">📅</div><h3>Easy Scheduling</h3><p>Book appointments with your doctor in just a few clicks.</p><button>Learn More</button></div>
                        <div class="feature-card"><div class="icon">🔒</div><h3>Secure Records</h3><p>Your medical history, always safe and accessible.</p><button>Learn More</button></div>
                        <div class="feature-card"><div class="icon">💬</div><h3>Direct Messaging</h3><p>Communicate with your care team without the wait.</p><button>Learn More</button></div>
                    </section>
                    <section class="services">
                        <h2>Our Services</h2>
                        <div class="service-grid">
                            <div class="service-item"><div class="img-placeholder">🩺</div><div class="info"><h4>General Checkup</h4><p>Routine visits & screenings</p></div></div>
                            <div class="service-item"><div class="img-placeholder">🧑‍⚕️</div><div class="info"><h4>Specialist Consults</h4><p>Cardiology, dermatology & more</p></div></div>
                            <div class="service-item"><div class="img-placeholder">🧪</div><div class="info"><h4>Lab Tests</h4><p>Fast, accurate diagnostics</p></div></div>
                            <div class="service-item"><div class="img-placeholder">🚑</div><div class="info"><h4>Emergency Care</h4><p>24/7 urgent support</p></div></div>
                        </div>
                    </section>
                    <footer>
                        <h2>Ready to take control of your healthcare?</h2>
                        <a href="/signup">Sign Up Now</a>
                    </footer>
                </body>
                </html>
                """;
    }

    @GetMapping("/signup")
    public String signup() {
        return """
                <!DOCTYPE html>
                <html>
                <head>
                    <title>Sign Up - MediCare Hub</title>
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <style>
                        * { margin: 0; padding: 0; box-sizing: border-box; }
                        body { font-family: 'Segoe UI', Arial, sans-serif; background: #f5f5f2; display: flex; align-items: center; justify-content: center; min-height: 100vh; }
                        .auth-card { background: #fff; padding: 40px; border-radius: 12px; box-shadow: 0 4px 20px rgba(0,0,0,0.08); width: 380px; }
                        .auth-card .logo { text-align: center; font-size: 22px; font-weight: bold; color: #1a5c4a; margin-bottom: 20px; }
                        label { display: block; font-size: 13px; margin-bottom: 5px; color: #555; margin-top: 12px; }
                        input { width: 100%; padding: 10px 12px; border: 1px solid #ddd; border-radius: 6px; font-size: 14px; }
                        .terms { display: flex; align-items: center; gap: 8px; margin-top: 15px; font-size: 13px; color: #666; }
                        .submit-btn { width: 100%; background: #e08a3e; color: white; border: none; padding: 12px; border-radius: 6px; font-weight: 600; margin-top: 20px; cursor: pointer; }
                        .switch-link { text-align: center; margin-top: 15px; font-size: 13px; color: #666; }
                        .switch-link a { color: #1a5c4a; font-weight: 600; text-decoration: none; }
                        .error-msg { color: #c0392b; font-size: 12px; margin-top: 5px; display: none; }
                    </style>
                </head>
                <body>
                    <div class="auth-card">
                        <div class="logo">MediCare Hub</div>
                        <form id="signupForm" onsubmit="return submitSignup(event)">
                            <label>Full Name</label>
                            <input type="text" id="fullName" required>
                            <label>Email</label>
                            <input type="email" id="email" required>
                            <label>Phone Number</label>
                            <input type="tel" id="phone" required>
                            <label>Password</label>
                            <input type="password" id="password" required>
                            <label>Confirm Password</label>
                            <input type="password" id="confirmPassword" required>
                            <div class="error-msg" id="passwordError">Passwords do not match</div>
                            <div class="terms">
                                <input type="checkbox" id="terms" required style="width:auto;">
                                <label style="margin:0;">I agree to the Terms and Conditions</label>
                            </div>
                            <button type="submit" class="submit-btn">Create Account</button>
                        </form>
                        <div class="switch-link">Already have an account? <a href="/login">Log In</a></div>
                    </div>
                    <script>
                        function submitSignup(event) {
                            event.preventDefault();
                            const password = document.getElementById('password').value;
                            const confirmPassword = document.getElementById('confirmPassword').value;
                            const errorMsg = document.getElementById('passwordError');

                            if (password !== confirmPassword) {
                                errorMsg.style.display = 'block';
                                return false;
                            }
                            errorMsg.style.display = 'none';

                            fetch('/api/signup', {
                                method: 'POST',
                                headers: { 'Content-Type': 'application/json' },
                                body: JSON.stringify({
                                    fullName: document.getElementById('fullName').value,
                                    email: document.getElementById('email').value,
                                    password: password,
                                    phone: document.getElementById('phone').value
                                })
                            })
                            .then(response => response.json())
                            .then(result => {
                                alert(result.message);
                                if (result.success) {
                                    window.location.href = '/login';
                                }
                            })
                            .catch(error => alert('Error: ' + error));

                            return false;
                        }
                    </script>
                </body>
                </html>
                """;
    }

    @GetMapping("/login")
    public String login() {
        return """
                <!DOCTYPE html>
                <html>
                <head>
                    <title>Login - MediCare Hub</title>
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <style>
                        * { margin: 0; padding: 0; box-sizing: border-box; }
                        body { font-family: 'Segoe UI', Arial, sans-serif; background: #f5f5f2; display: flex; align-items: center; justify-content: center; min-height: 100vh; }
                        .auth-card { background: #fff; padding: 40px; border-radius: 12px; box-shadow: 0 4px 20px rgba(0,0,0,0.08); width: 380px; }
                        .auth-card .logo { text-align: center; font-size: 22px; font-weight: bold; color: #1a5c4a; margin-bottom: 25px; }
                        label { display: block; font-size: 13px; margin-bottom: 5px; color: #555; margin-top: 12px; }
                        input { width: 100%; padding: 10px 12px; border: 1px solid #ddd; border-radius: 6px; font-size: 14px; }
                        .row-between { display: flex; justify-content: space-between; align-items: center; margin-top: 12px; font-size: 13px; }
                        .row-between a { color: #1a5c4a; text-decoration: none; }
                        .submit-btn { width: 100%; background: #1a5c4a; color: white; border: none; padding: 12px; border-radius: 6px; font-weight: 600; margin-top: 20px; cursor: pointer; }
                        .switch-link { text-align: center; margin-top: 15px; font-size: 13px; color: #666; }
                        .switch-link a { color: #1a5c4a; font-weight: 600; text-decoration: none; }
                    </style>
                </head>
                <body>
                    <div class="auth-card">
                        <div class="logo">MediCare Hub</div>
                        <form id="loginForm" onsubmit="return submitLogin(event)">
                            <label>Email</label>
                            <input type="email" id="email" required>
                            <label>Password</label>
                            <input type="password" id="password" required>
                            <div class="row-between">
                                <label style="margin:0; display:flex; align-items:center; gap:5px;">
                                    <input type="checkbox" id="rememberMe" style="width:auto;"> Remember me
                                </label>
                                <a href="#">Forgot Password?</a>
                            </div>
                            <button type="submit" class="submit-btn">Log In</button>
                        </form>
                        <div class="switch-link">Don't have an account? <a href="/signup">Sign Up</a></div>
                    </div>
                    <script>
                        function submitLogin(event) {
                            event.preventDefault();
                            fetch('/api/login', {
                                method: 'POST',
                                headers: { 'Content-Type': 'application/json' },
                                body: JSON.stringify({
                                    email: document.getElementById('email').value,
                                    password: document.getElementById('password').value
                                })
                            })
                            .then(response => response.json())
                            .then(result => {
                                alert(result.message);
                                if (result.success) {
                                    window.location.href = '/patients';
                                }
                            })
                            .catch(error => alert('Error: ' + error));

                            return false;
                        }
                    </script>
                </body>
                </html>
                """;
    }

    @GetMapping("/patients")
    public String patientDashboard() {
        return """
                <!DOCTYPE html>
                <html>
                <head>
                    <title>Patient Dashboard - MediCare Hub</title>
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <style>
                        * { margin: 0; padding: 0; box-sizing: border-box; }
                        body { font-family: 'Segoe UI', Arial, sans-serif; background: #eef1f0; display: flex; }
                        .sidebar { width: 80px; background: #1a4d3e; height: 100vh; display: flex; flex-direction: column; align-items: center; padding-top: 25px; gap: 30px; position: fixed; }
                        .sidebar .logo { font-size: 26px; margin-bottom: 20px; }
                        .sidebar a { color: #cfe3da; text-decoration: none; font-size: 20px; }
                        .sidebar a.active { color: #fff; }
                        .main { margin-left: 80px; padding: 30px 40px; width: 100%; }
                        .topbar { display: flex; justify-content: space-between; align-items: center; margin-bottom: 25px; }
                        .topbar h1 { font-size: 22px; color: #2c3e50; }
                        .topbar .profile { display: flex; align-items: center; gap: 10px; font-weight: 600; }
                        .top-row { display: flex; gap: 20px; margin-bottom: 20px; flex-wrap: wrap; }
                        .card { background: #fff; border-radius: 12px; padding: 20px; box-shadow: 0 2px 8px rgba(0,0,0,0.05); }
                        .patient-card { text-align: center; width: 200px; }
                        .patient-card .avatar { width: 70px; height: 70px; border-radius: 50%; background: #d8e6df; margin: 0 auto 10px; display: flex; align-items: center; justify-content: center; font-size: 30px; }
                        .patient-card h3 { margin-bottom: 4px; }
                        .patient-card p { color: #888; font-size: 13px; margin-bottom: 12px; }
                        .patient-card button { background: #3aa17e; color: white; border: none; padding: 8px 20px; border-radius: 6px; cursor: pointer; font-weight: 600; }
                        .vital-card { flex: 1; min-width: 150px; text-align: center; }
                        .vital-card .icon { font-size: 22px; margin-bottom: 8px; }
                        .vital-card .label { color: #888; font-size: 13px; margin-bottom: 6px; }
                        .vital-card .value { font-size: 22px; font-weight: bold; }
                        .vital-card .value span { font-size: 13px; font-weight: normal; color: #888; }
                        .bottom-row { display: flex; gap: 20px; flex-wrap: wrap; }
                        .bottom-row .card { flex: 1; min-width: 300px; }
                        .info-list { display: grid; grid-template-columns: 1fr 1fr; gap: 10px; margin-top: 12px; font-size: 14px; }
                        .info-list div span { display: block; color: #999; font-size: 12px; }
                        .list-item { display: flex; justify-content: space-between; align-items: center; padding: 10px 0; border-bottom: 1px solid #eee; font-size: 14px; }
                        .list-item:last-child { border-bottom: none; }
                        .add-btn { width: 100%; padding: 10px; margin-top: 10px; border: 1px dashed #bbb; background: none; border-radius: 6px; cursor: pointer; color: #555; }
                    </style>
                </head>
                <body>
                    <div class="sidebar">
                        <div class="logo">🏥</div>
                        <a href="/patients" class="active">📊</a>
                        <a href="#">👥</a>
                        <a href="#">📈</a>
                        <a href="#">📄</a>
                        <a href="#">📅</a>
                        <a href="/">🏠</a>
                    </div>
                    <div class="main">
                        <div class="topbar">
                            <h1>Current Appointment</h1>
                            <div class="profile">👤 Roger Curtis</div>
                        </div>
                        <div class="top-row">
                            <div class="card patient-card">
                                <div class="avatar">🧑</div>
                                <h3>Roger Curtis</h3>
                                <p>Age: 36</p>
                                <button onclick="alert('Vitals updated!')">Update</button>
                            </div>
                            <div class="card vital-card"><div class="icon">❤️</div><div class="label">Heart Rate</div><div class="value">80<span>bpm</span></div></div>
                            <div class="card vital-card"><div class="icon">🌡️</div><div class="label">Body Temperature</div><div class="value">36.5<span>°C</span></div></div>
                            <div class="card vital-card"><div class="icon">🩸</div><div class="label">Glucose</div><div class="value">100<span>mg/dl</span></div></div>
                        </div>
                        <div class="bottom-row">
                            <div class="card">
                                <h3>Information</h3>
                                <div class="info-list">
                                    <div><span>Gender</span>Male</div>
                                    <div><span>Blood Type</span>O+ (Positive)</div>
                                    <div><span>Allergies</span>Milk, Penicillin</div>
                                    <div><span>Diseases</span>Diabetes, Blood Disorders</div>
                                    <div><span>Height</span>1.78m</div>
                                    <div><span>Weight</span>65 kg</div>
                                    <div><span>Patient ID</span>208898786</div>
                                    <div><span>Last Visit</span>25th October 2019</div>
                                </div>
                            </div>
                            <div class="card">
                                <h3>Test Reports</h3>
                                <div class="list-item"><span>🧬 CT Scan - Full Body</span><span>12 Feb 2020</span></div>
                                <div class="list-item"><span>🧪 Creatine Kinase T</span><span>12 Feb 2020</span></div>
                                <div class="list-item"><span>👁️ Eye Fluorescein Test</span><span>12 Feb 2020</span></div>
                                <h3 style="margin-top:20px;">Prescriptions</h3>
                                <button class="add-btn" onclick="addPrescription()">+ Add a prescription</button>
                                <div id="prescriptionList">
                                    <div class="list-item"><span>💊 Heart Diseases</span><span>25 Oct 2019 · 3 months</span></div>
                                    <div class="list-item"><span>💊 Skin Care</span><span>8 Aug 2019 · 2 months</span></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <script>
                        function addPrescription() {
                            const name = prompt("Prescription name:");
                            if (!name) return;
                            const duration = prompt("Duration (e.g. 1 month):") || "N/A";
                            const list = document.getElementById("prescriptionList");
                            const item = document.createElement("div");
                            item.className = "list-item";
                            item.innerHTML = "<span>💊 " + name + "</span><span>" + new Date().toLocaleDateString() + " · " + duration + "</span>";
                            list.appendChild(item);
                        }
                    </script>
                </body>
                </html>
                """;
    }
}