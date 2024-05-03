package com.example.hospital_management_system;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.hospital_management_system.entity.*;
import com.example.hospital_management_system.repository.*;


@SpringBootApplication
public class HospitalManagementSystemApplication implements CommandLineRunner {
	
	private final UserRepository userRepository;
	private final InsuranceServerRepository insuranceServerRepository;
	private final PoliclinicRepository policlinicRepository;
	private final ClinicRepository clinicRepository;
	private final StaffRepository staffRepository;
	private final DoctorRepository doctorRepository;
	private final PatientRepository patientRepository;
	private final PaymentRepository paymentRepository;
	private final AppointmentRepository appointmentRepository;
	private final TreatmentRepository treatmentRepository;
	private final MedicalProcedureRepository medicalProcedureRepository;
	private final MedicalProcedureTreatmentRepository medicalProcedureTreatmentRepository;
	
	
	
	@Autowired
	public HospitalManagementSystemApplication(UserRepository userRepository, InsuranceServerRepository insuranceServerRepository, PoliclinicRepository policlinicRepository, ClinicRepository clinicRepository, StaffRepository staffRepository, DoctorRepository doctorRepository, PatientRepository patientRepository, PaymentRepository paymentRepository, AppointmentRepository appointmentRepository, TreatmentRepository treatmentRepository, MedicalProcedureRepository medicalProcedureRepository, MedicalProcedureTreatmentRepository medicalProcedureTreatmentRepository) {
	    this.userRepository = userRepository;
		this.insuranceServerRepository = insuranceServerRepository;
		this.policlinicRepository = policlinicRepository;
		this.clinicRepository = clinicRepository;
		this.staffRepository = staffRepository;
		this.doctorRepository = doctorRepository;
		this.patientRepository = patientRepository;
		this.paymentRepository = paymentRepository;
		this.appointmentRepository = appointmentRepository;
		this.treatmentRepository = treatmentRepository;
		this.medicalProcedureRepository = medicalProcedureRepository;
		this.medicalProcedureTreatmentRepository = medicalProcedureTreatmentRepository;
	}



    public static void main(String[] args) {
        SpringApplication.run(HospitalManagementSystemApplication.class, args);
    }

    
    // This section is for testing the database. Those that I wrote in the comment line has been tested.
    // Test them one by one to test for yourself.
    @Override
    public void run(String... args) throws Exception {
        //User user = userRepository.findById(1L).orElse(null);
    	//InsuranceServer insuranceServer = insuranceServerRepository.findById(1L).orElse(null);
    	//Policlinic policlinic = policlinicRepository.findById(1L).orElse(null);
    	//Clinic clinic = clinicRepository.findById(1L).orElse(null);
    	//Staff staff = staffRepository.findById(1L).orElse(null);
    	//Doctor doctor = doctorRepository.findById(1L).orElse(null);
    	//Patient patient = patientRepository.findBySocialNumber("12345678901"); // Since the Patient ID is String, we defined a special method
    	//Payment payment = paymentRepository.findById(1L).orElse(null);
    	//Appointment appointment = appointmentRepository.findById(1L).orElse(null);
    	//Treatment treatment = treatmentRepository.findById(1L).orElse(null);
    	MedicalProcedure medicalProcedure = medicalProcedureRepository.findById(1L).orElse(null);
 

        
       if (medicalProcedure != null) {
            System.out.println(medicalProcedure);
            System.out.println(medicalProcedure.getName());
        } else {
            System.out.println("Kullanıcı bulunamadı.");
        } 
       
       // MedicalProcedureTreatment iki tane anahtara sahip olduğundan testi aşağıdaki gibi olmalı
   	    Optional<MedicalProcedureTreatment> result = medicalProcedureTreatmentRepository.findByMedicalProcedureIdAndTreatmentId(1L, 1L);
   	    result.ifPresent(System.out::println);
    }
}
