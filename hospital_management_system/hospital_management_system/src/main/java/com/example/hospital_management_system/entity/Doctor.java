package com.example.hospital_management_system.entity;

import java.util.Map;

import jakarta.persistence.*;

@Entity
@Table(name="Doctor")
public class Doctor {

    @Id
    @Column(length = 11)
    private String id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String specialty;

    @Column(nullable = false)
    private String qualification;

    @Lob
    @Column(nullable = false)
    private Map<String, int[]> workingHours;

    @ManyToOne
    @JoinColumn(name = "clinicID", nullable = false)
    private Clinic clinic;

    // Constructors, getters, setters
    public Doctor() {
    }

    public Doctor(String id, String firstName, String lastName, String address, String specialty, String qualification, Map<String, int[]> workingHours, Clinic clinic) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.specialty = specialty;
        this.qualification = qualification;
        this.workingHours = workingHours;
        this.clinic = clinic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public Map<String, int[]>  getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Map<String, int[]>  workingHours) {
        this.workingHours = workingHours;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", specialty='" + specialty + '\'' +
                ", qualification='" + qualification + '\'' +
                ", workingHours='" + workingHours + '\'' +
                ", clinic=" + clinic +
                '}';
    }
}
