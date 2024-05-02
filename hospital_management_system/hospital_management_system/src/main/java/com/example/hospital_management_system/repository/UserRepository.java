package com.example.hospital_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hospital_management_system.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
