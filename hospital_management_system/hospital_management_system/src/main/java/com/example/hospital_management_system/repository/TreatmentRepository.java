package com.example.hospital_management_system.repository;

import com.example.hospital_management_system.entity.Treatment;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
    List<Treatment> findByPatientSocialNumberOrderByTreatmentDateDesc(String socialNumber);
    Optional<Treatment> findByPatientSocialNo(String social_no);
}