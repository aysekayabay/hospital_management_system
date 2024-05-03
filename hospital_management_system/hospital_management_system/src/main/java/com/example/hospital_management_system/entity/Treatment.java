package com.example.hospital_management_system.entity;


import jakarta.persistence.*;
import java.util.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "treatment")
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "diagnosis", length = 50, nullable = false)
    private String diagnosis;

    @Column(name = "prescription", length = 255)
    private String prescription;

    @Column(name = "report", length = 255, nullable = false)
    private String report;

    @Column(name = "referralnote", length = 255, nullable = false)
    private String referralNote;

    @Column(name = "treatmentdate", nullable = false)
    private Timestamp treatmentDate;

    @ManyToOne
    @JoinColumn(name = "clinicid")
    private Clinic clinic;

    @ManyToOne
    @JoinColumn(name = "doctorid")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "paymentid")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "appointmentid")
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "patientsocialnumber")
    private Patient patient;

    @Column(name = "treatmentduration", nullable = false)
    private Time treatmentDuration;

    // Constructors, getters, setters
    public Treatment() {
    }

    public Treatment(String diagnosis, String prescription, String report, String referralNote, Timestamp treatmentDate, Clinic clinic, Doctor doctorID, Payment payment, Appointment appointment, Patient patient, Time treatmentDuration) {
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.report = report;
        this.referralNote = referralNote;
        this.treatmentDate = treatmentDate;
        this.clinic = clinic;
        this.doctor = doctorID;
        this.payment = payment;
        this.appointment = appointment;
        this.patient = patient;
        this.treatmentDuration = treatmentDuration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getReferralNote() {
        return referralNote;
    }

    public void setReferralNote(String referralNote) {
        this.referralNote = referralNote;
    }

    public Date getTreatmentDate() {
        return treatmentDate;
    }

    public void setTreatmentDate(Timestamp treatmentDate) {
        this.treatmentDate = treatmentDate;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Doctor getDoctorID() {
        return doctor;
    }

    public void setDoctorID(Doctor doctorID) {
        this.doctor = doctorID;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Time getTreatmentDuration() {
        return treatmentDuration;
    }

    public void setTreatmentDuration(Time treatmentDuration) {
        this.treatmentDuration = treatmentDuration;
    }

    @Override
    public String toString() {
        return "Treatment{" +
                "id=" + id +
                ", diagnosis='" + diagnosis + '\'' +
                ", prescription='" + prescription + '\'' +
                ", report='" + report + '\'' +
                ", referralNote='" + referralNote + '\'' +
                ", treatmentDate=" + treatmentDate +
                ", clinic=" + clinic +
                ", doctor='" + (doctor != null ? doctor.getId() : null) + '\'' +
                ", payment=" +  (payment != null ? payment.getId() : null) +
                ", appointment=" + (appointment != null ? appointment.getId() : null) +
                ", patient=" + (patient != null ? patient.getSocialNumber() : null) +
                ", treatmentDuration=" + treatmentDuration +
                '}';
    }
} 
