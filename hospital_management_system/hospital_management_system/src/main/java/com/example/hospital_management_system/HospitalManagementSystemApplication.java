package com.example.hospital_management_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.example.hospital_management_system.entity.Doctor;
import com.example.hospital_management_system.repository.*;

import ui.*;
import ui.LoginPage;

@SpringBootApplication
public class HospitalManagementSystemApplication implements CommandLineRunner {
	private final HospitalManagementService hospitalManagementService;
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
	public HospitalManagementSystemApplication(InsuranceServerRepository insuranceServerRepository,
			PoliclinicRepository policlinicRepository, ClinicRepository clinicRepository,
			StaffRepository staffRepository, DoctorRepository doctorRepository, PatientRepository patientRepository,
			PaymentRepository paymentRepository, AppointmentRepository appointmentRepository,
			TreatmentRepository treatmentRepository, MedicalProcedureRepository medicalProcedureRepository,
			MedicalProcedureTreatmentRepository medicalProcedureTreatmentRepository,
			HospitalManagementService hospitalManagementService // Servis sınıfını enjekte edin
	) {
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
		this.hospitalManagementService = hospitalManagementService; 
	}

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(HospitalManagementSystemApplication.class);
		builder.headless(false);
		builder.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// Id'si 1 olan doktoru al
//		long doctorId = 1L;
//		String password = "doctor1";
//        Doctor doctor = hospitalManagementService.getDoctorRepository().findByIdAndPassword(doctorId, password);
//        if (doctor != null) {
//            // UI'ı başlat ve doktoru gönder
//           DoctorPage window = new DoctorPage(doctor, hospitalManagementService);
//           window.getFrame().setVisible(true);
//        } 

    	InfoDeskPage login = new InfoDeskPage(hospitalManagementService);
    	login.getFrame().setVisible(true);
	}
}