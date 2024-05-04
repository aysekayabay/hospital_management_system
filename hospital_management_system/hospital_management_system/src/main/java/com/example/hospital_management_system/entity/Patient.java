package com.example.hospital_management_system.entity;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @Column(name = "socialnumber", length = 11)
    private String socialNumber;

    @Column(name = "firstname", length = 25, nullable = false)
    private String firstName;

    @Column(name = "lastname", length = 25, nullable = false)
    private String lastName;

    @Column(name = "gender", length = 25, nullable = false)
    private String gender;

    @Column(name = "address", length = 50, nullable = false)
    private String address;

    @Column(name = "birthdate", nullable = false)
    private Date birthDate;

    @Column(name = "email", length = 25, nullable = false)
    private String email;

    @Column(name = "phone", length = 11, nullable = false)
    private String phone;
    // Constructors, getters, setters
    public Patient() {
    }

    public Patient(String socialNumber, String firstName, String lastName, String gender, String address, Date birthDate, String email, String phone) {
        this.socialNumber = socialNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.address = address;
        this.birthDate = birthDate;
        this.email = email;
        this.phone = phone;
    }

    public String getSocialNumber() {
        return socialNumber;
    }

    public void setSocialNumber(String socialNumber) {
        this.socialNumber = socialNumber;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "socialNumber='" + socialNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
} 
