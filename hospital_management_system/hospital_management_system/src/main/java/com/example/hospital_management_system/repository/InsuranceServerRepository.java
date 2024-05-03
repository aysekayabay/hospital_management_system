package com.example.hospital_management_system.repository;

import com.example.hospital_management_system.entity.InsuranceServer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceServerRepository extends JpaRepository<InsuranceServer, Long> {
}
