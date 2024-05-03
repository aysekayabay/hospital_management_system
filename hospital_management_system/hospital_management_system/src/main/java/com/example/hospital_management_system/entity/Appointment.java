package com.example.hospital_management_system.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "appointmentdate", nullable = false)
    private Timestamp appointmentDate;

    @ManyToOne
    @JoinColumn(name = "doctorid")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "clinicid")
    private Clinic clinic;

    @ManyToOne
    @JoinColumn(name = "patientsocialnumber")
    private Patient patient;

    // Constructors, getters, setters
    public Appointment() {
    }

    public Appointment(Timestamp appointmentDate, Doctor doctorID, Clinic clinic, Patient patient) {
        this.appointmentDate = appointmentDate;
        this.doctor = doctorID;
        this.clinic = clinic;
        this.patient = patient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Timestamp appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Doctor getDoctorID() {
        return doctor;
    }

    public void setDoctorID(Doctor doctor) {
        this.doctor = doctor;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", appointmentDate=" + appointmentDate +
                ", doctor='" + (doctor != null ? doctor.getId() : null) + '\'' +
                ", clinic=" + (clinic != null ? clinic.getId() : null) +
                ", patient=" + (patient != null ? patient.getSocialNumber() : null) +
                '}';
    }
} 
