package com.example.hospital_management_system.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Policlinic")
public class Policlinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @ManyToOne
    @JoinColumn(name = "collabratedInsuranceServerID")
    private InsuranceServer collabratedInsuranceServer;

    // Constructors, getters, setters
    public Policlinic() {
    }

    public Policlinic(String name, String location, InsuranceServer collabratedInsuranceServer) {
        this.name = name;
        this.location = location;
        this.collabratedInsuranceServer = collabratedInsuranceServer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public InsuranceServer getCollabratedInsuranceServer() {
        return collabratedInsuranceServer;
    }

    public void setCollabratedInsuranceServer(InsuranceServer collabratedInsuranceServer) {
        this.collabratedInsuranceServer = collabratedInsuranceServer;
    }

    @Override
    public String toString() {
        return "Policlinic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", collabratedInsuranceServer=" + collabratedInsuranceServer +
                '}';
    }
}
