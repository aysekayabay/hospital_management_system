package com.example.hospital_management_system.repository;

import com.example.hospital_management_system.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long> {
	Clinic findByname(String name);
}
