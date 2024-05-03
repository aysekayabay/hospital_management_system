package com.example.hospital_management_system.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "insuranceserver")
public class InsuranceServer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 25, nullable = false)
    private String name;

    @Column(name = "peoplewithinsurance", length = 25, nullable = false)
    private List<String> peopleWithInsurance;

    // Constructors, getters, setters
    public InsuranceServer() {
    }

    public InsuranceServer(String name, List<String>  peopleWithInsurance) {
        this.name = name;
        this.peopleWithInsurance = peopleWithInsurance;
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

    public List<String>  getPeopleWithInsurance() {
        return peopleWithInsurance;
    }

    public void setPeopleWithInsurance(List<String>  peopleWithInsurance) {
        this.peopleWithInsurance = peopleWithInsurance;
    }

    @Override
    public String toString() {
        return "InsuranceServer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", peopleWithInsurance=" + peopleWithInsurance +
                '}';
    }
}
