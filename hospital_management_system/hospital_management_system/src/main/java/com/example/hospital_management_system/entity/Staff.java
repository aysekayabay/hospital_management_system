package com.example.hospital_management_system.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Staff")
public class Staff {

    @Id
    @Column(length = 11)
    private String id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "policlinicID", nullable = false)
    private Policlinic policlinic;

    // Constructors, getters, setters
    public Staff() {
    }

    public Staff(String id, String firstName, String lastName, String address, Policlinic policlinic) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.policlinic = policlinic;
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

    public Policlinic getPoliclinic() {
        return policlinic;
    }

    public void setPoliclinic(Policlinic policlinic) {
        this.policlinic = policlinic;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", policlinic=" + policlinic +
                '}';
    }
}
