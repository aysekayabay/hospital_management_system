package com.example.hospital_management_system.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "policlinic")
public class Policlinic {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 25, nullable = false)
    private String name;

    @Column(name = "location", length = 100, nullable = false)
    private String location;

    @ManyToOne
    @JoinColumn(name = "collaboratedinsuranceserverid")
    private InsuranceServer collaboratedInsuranceServer;

    // Constructors, getters, setters
    public Policlinic() {
    }

    public Policlinic(String name, String location, InsuranceServer collabratedInsuranceServer) {
        this.name = name;
        this.location = location;
        this.collaboratedInsuranceServer = collabratedInsuranceServer;
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
        return collaboratedInsuranceServer;
    }

    public void setCollabratedInsuranceServer(InsuranceServer collabratedInsuranceServer) {
        this.collaboratedInsuranceServer = collabratedInsuranceServer;
    }

    @Override
    public String toString() {
        return "Policlinic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", collabratedInsuranceServer=" + collaboratedInsuranceServer +
                '}';
    }
} 
