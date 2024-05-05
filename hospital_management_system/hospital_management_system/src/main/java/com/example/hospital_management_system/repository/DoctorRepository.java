package com.example.hospital_management_system.repository;

import com.example.hospital_management_system.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	Doctor findByIdAndPassword(Long id, String password);
}