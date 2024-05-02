package com.example.hospital_management_system.entity;

import jakarta.persistence.*;

@Entity
@Table(name="MedicalProcedure")
public class MedicalProcedure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int cost;

    @ManyToOne
    @JoinColumn(name = "policlinicID", nullable = false)
    private Policlinic policlinic;

    // Constructors, getters, setters
    public MedicalProcedure() {
    }

    public MedicalProcedure(String name, int cost, Policlinic policlinic) {
        this.name = name;
        this.cost = cost;
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Policlinic getPoliclinic() {
        return policlinic;
    }

    public void setPoliclinic(Policlinic policlinic) {
        this.policlinic = policlinic;
    }

    @Override
    public String toString() {
        return "MedicalProcedure{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", policlinic=" + policlinic +
                '}';
    }
}
