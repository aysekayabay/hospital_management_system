package com.example.hospital_management_system.entity;


import jakarta.persistence.*;
import java.util.Date;
import java.sql.Time;

@Entity
@Table(name="Appointment")
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String diagnosis;

    @Column
    private String prescription;

    @Column(nullable = false)
    private String report;

    @Column(nullable = false)
    private String referralNote;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date treatmentDate;

    @ManyToOne
    @JoinColumn(name = "clinicID", nullable = false)
    private Clinic clinic;

    @Column(nullable = false)
    private String doctorID;

    @ManyToOne
    @JoinColumn(name = "paymentID", nullable = false)
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "appointmentID", nullable = false)
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "patientsocialNumber", nullable = false)
    private Patient patient;

    @Column(nullable = false)
    private Time treatmentDuration;

    // Constructors, getters, setters
    public Treatment() {
    }

    public Treatment(String diagnosis, String prescription, String report, String referralNote, Date treatmentDate, Clinic clinic, String doctorID, Payment payment, Appointment appointment, Patient patient, Time treatmentDuration) {
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.report = report;
        this.referralNote = referralNote;
        this.treatmentDate = treatmentDate;
        this.clinic = clinic;
        this.doctorID = doctorID;
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

    public void setTreatmentDate(Date treatmentDate) {
        this.treatmentDate = treatmentDate;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
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
                ", doctorID='" + doctorID + '\'' +
                ", payment=" + payment +
                ", appointment=" + appointment +
                ", patient=" + patient +
                ", treatmentDuration=" + treatmentDuration +
                '}';
    }
}
