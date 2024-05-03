package com.example.hospital_management_system.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "clinic")
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 25, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "policlinicid")
    private Policlinic policlinic;

    // Constructors, getters, setters
    public Clinic() {
    }

    public Clinic(String name, Policlinic policlinic) {
        this.name = name;
        this.policlinic = policlinic;
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

    public Policlinic getPoliclinic() {
        return policlinic;
    }

    public void setPoliclinic(Policlinic policlinic) {
        this.policlinic = policlinic;
    }

    @Override
    public String toString() {
        return "Clinic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", policlinic=" + policlinic +
                '}';
    }
}
