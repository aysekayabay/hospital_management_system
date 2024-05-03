package com.example.hospital_management_system.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "medicalproceduretreatment")
@IdClass(MedicalProcedureTreatmentId.class) // Define the primary key class
public class MedicalProcedureTreatment implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "medicalprocedureid")
    private MedicalProcedure medicalProcedure;

    @Id
    @ManyToOne
    @JoinColumn(name = "treatmentid")
    private Treatment treatment;

    // Constructors, getters, setters
    public MedicalProcedureTreatment() {
    }

    public MedicalProcedureTreatment(MedicalProcedure medicalProcedure, Treatment treatment) {
        this.medicalProcedure = medicalProcedure;
        this.treatment = treatment;
    }

    public MedicalProcedure getMedicalProcedure() {
        return medicalProcedure;
    }

    public void setMedicalProcedure(MedicalProcedure medicalProcedure) {
        this.medicalProcedure = medicalProcedure;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    @Override
    public String toString() {
        return "MedicalProcedureTreatment{" +
                "medicalProcedure=" + medicalProcedure +
                ", treatment=" + (treatment != null ? treatment.getId() : null) +
                '}';
    }
} 
