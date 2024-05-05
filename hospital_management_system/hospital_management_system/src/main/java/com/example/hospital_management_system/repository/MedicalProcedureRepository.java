package com.example.hospital_management_system.repository;
	
import com.example.hospital_management_system.entity.MedicalProcedure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalProcedureRepository extends JpaRepository<MedicalProcedure, Long> {
    MedicalProcedure findByName(String name);
    List<MedicalProcedure> findByPoliclinicId(int policlinicId);
    Optional<MedicalProcedure> findByMedicalProcedureId(Long medicalProcedureId);
}