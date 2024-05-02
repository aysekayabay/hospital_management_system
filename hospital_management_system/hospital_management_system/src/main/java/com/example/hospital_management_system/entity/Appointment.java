package com.example.hospital_management_system.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name="Appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date appointmentDate;

    @Column(nullable = false)
    private String doctorID;

    @ManyToOne
    @JoinColumn(name = "clinicID", nullable = false)
    private Clinic clinic;

    @ManyToOne
    @JoinColumn(name = "patientsocialNumber", nullable = false)
    private Patient patient;

    // Constructors, getters, setters
    public Appointment() {
    }

    public Appointment(Date appointmentDate, String doctorID, Clinic clinic, Patient patient) {
        this.appointmentDate = appointmentDate;
        this.doctorID = doctorID;
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

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
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
                ", doctorID='" + doctorID + '\'' +
                ", clinic=" + clinic +
                ", patient=" + patient +
                '}';
    }
}
