package com.example.hospital_management_system.repository;

import com.example.hospital_management_system.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
	Patient findBySocialNumber(String socialNumber); // Since the Patient ID is String, we defined a special method
}