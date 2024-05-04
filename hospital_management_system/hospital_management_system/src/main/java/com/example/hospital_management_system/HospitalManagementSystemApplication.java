package com.example.hospital_management_system;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.example.hospital_management_system.entity.*;
import com.example.hospital_management_system.repository.*;

import ui.ViewPatientAndAddRecordPage;
@SpringBootApplication
public class HospitalManagementSystemApplication implements CommandLineRunner {
	
	private final PatientRepository patientRepository;

    public HospitalManagementSystemApplication(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public static void main(String[] args) {
    	SpringApplicationBuilder builder =new SpringApplicationBuilder(HospitalManagementSystemApplication.class);
    	builder.headless(false);
        builder.run(args);
    }
   
    @Override
    public void run(String... args) throws Exception {
        // Id'si 1 olan hastayı al
        Patient patient = patientRepository.findBySocialNumber("12345678901");
        System.out.println("!!!!!!!!! " + patient.getFirstName());
        if (patient != null) {
            // UI'ı başlat ve hastayı gönder
            ViewPatientAndAddRecordPage window = new ViewPatientAndAddRecordPage(patient);
            window.getFrame().setVisible(true);
        } 
    }
}