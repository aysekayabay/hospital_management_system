package com.example.hospital_management_system.entity;

import java.util.Map;
import com.example.hospital_management_system.converter.*;
import jakarta.persistence.*;

@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "password", length = 25, nullable = false)
    private String password;

    @Column(name = "firstname", length = 25, nullable = false)
    private String firstName;

    @Column(name = "lastname", length = 25, nullable = false)
    private String lastName;

    @Column(name = "address", length = 100, nullable = false)
    private String address;

    @Column(name = "specialty", length = 50, nullable = false)
    private String specialty;

    @Column(name = "qualification", length = 50, nullable = false)
    private String qualification;

    @Convert(converter = JsonMapConverter.class)
    @Column(name = "workinghours", nullable = false)
    private Map<String, String> workingHours;
    
    
    @ManyToOne
    @JoinColumn(name = "clinicid")
    private Clinic clinic;

    // Constructors, getters, setters
    public Doctor() {
    }

    public Doctor(Long id, String firstName, String lastName, String address, String specialty, String qualification, Map<String, String> workingHours, Clinic clinic) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.specialty = specialty;
        this.qualification = qualification;
        this.workingHours = workingHours;
        this.clinic = clinic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    
    
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public Map<String, String>  getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Map<String, String>  workingHours) {
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
