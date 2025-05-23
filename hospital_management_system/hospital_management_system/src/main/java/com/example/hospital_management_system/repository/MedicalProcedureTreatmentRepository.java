package com.example.hospital_management_system.repository;

import com.example.hospital_management_system.entity.MedicalProcedureTreatment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalProcedureTreatmentRepository extends JpaRepository<MedicalProcedureTreatment, Long> {

    MedicalProcedureTreatment findByMedicalProcedureIdAndTreatmentId(Long medicalProcedureId, Long treatmentId);
    MedicalProcedureTreatment findByMedicalProcedureId(Long medicalProcedureId);
    List<MedicalProcedureTreatment> findByTreatmentId(Long treatmentId);
    List<MedicalProcedureTreatment> findListByTreatmentId(Long treatmentId);
}
