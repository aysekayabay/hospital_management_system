package com.example.hospital_management_system.entity;

import java.io.Serializable;

public class MedicalProcedureTreatmentId implements Serializable {

    private Long medicalProcedure; 

    private Long treatment;
    
    
	public MedicalProcedureTreatmentId(Long medicalProcedure, Long treatment) {
		super();
		this.medicalProcedure = medicalProcedure;
		this.treatment = treatment;
	} 
    
	

	public MedicalProcedureTreatmentId() {
		super();
	}



	public Long getMedicalProcedure() {
		return medicalProcedure;
	}

	public void setMedicalProcedure(Long medicalProcedure) {
		this.medicalProcedure = medicalProcedure;
	}

	public Long getTreatment() {
		return treatment;
	}

	public void setTreatment(Long treatment) {
		this.treatment = treatment;
	}
    
    
    
}