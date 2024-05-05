package com.example.hospital_management_system.repository;

import com.example.hospital_management_system.entity.Appointment;
import com.example.hospital_management_system.entity.Doctor;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	List<Appointment> findByDoctor(Doctor doctor);
	List<Appointment> findByDoctorAndAppointmentDateBetween(Doctor doctor, Date startDate, Date endDate);
}