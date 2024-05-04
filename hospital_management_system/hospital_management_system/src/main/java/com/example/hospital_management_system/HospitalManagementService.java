package com.example.hospital_management_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospital_management_system.entity.Appointment;
import com.example.hospital_management_system.entity.Treatment;
import com.example.hospital_management_system.repository.AppointmentRepository;
import com.example.hospital_management_system.repository.ClinicRepository;
import com.example.hospital_management_system.repository.DoctorRepository;
import com.example.hospital_management_system.repository.InsuranceServerRepository;
import com.example.hospital_management_system.repository.MedicalProcedureRepository;
import com.example.hospital_management_system.repository.MedicalProcedureTreatmentRepository;
import com.example.hospital_management_system.repository.PatientRepository;
import com.example.hospital_management_system.repository.PaymentRepository;
import com.example.hospital_management_system.repository.PoliclinicRepository;
import com.example.hospital_management_system.repository.StaffRepository;
import com.example.hospital_management_system.repository.TreatmentRepository;

@Service
public class HospitalManagementService {

	private final AppointmentRepository appointmentRepository;
	private final ClinicRepository clinicRepository;
	private final DoctorRepository doctorRepository;
	private final InsuranceServerRepository insuranceServerRepository;
	private final MedicalProcedureRepository medicalProcedureRepository;
	private final MedicalProcedureTreatmentRepository medicalProcedureTreatmentRepository;
	private final PatientRepository patientRepository;
	private final PaymentRepository paymentRepository;
	private final PoliclinicRepository policlinicRepository;
	private final StaffRepository staffRepository;
    private final TreatmentRepository treatmentRepository;
    
    @Autowired
    public HospitalManagementService(AppointmentRepository appointmentRepository,
                                     ClinicRepository clinicRepository,
                                     DoctorRepository doctorRepository,
                                     InsuranceServerRepository insuranceServerRepository,
                                     MedicalProcedureRepository medicalProcedureRepository,
                                     MedicalProcedureTreatmentRepository medicalProcedureTreatmentRepository,
                                     PatientRepository patientRepository,
                                     PaymentRepository paymentRepository,
                                     PoliclinicRepository policlinicRepository,
                                     StaffRepository staffRepository,
                                     TreatmentRepository treatmentRepository) {
        this.appointmentRepository = appointmentRepository;
        this.clinicRepository = clinicRepository;
        this.doctorRepository = doctorRepository;
        this.insuranceServerRepository = insuranceServerRepository;
        this.medicalProcedureRepository = medicalProcedureRepository;
        this.medicalProcedureTreatmentRepository = medicalProcedureTreatmentRepository;
        this.patientRepository = patientRepository;
        this.paymentRepository = paymentRepository;
        this.policlinicRepository = policlinicRepository;
        this.staffRepository = staffRepository;
        this.treatmentRepository = treatmentRepository;
    }

    public void addTreatment(Treatment treatment) {
        treatmentRepository.save(treatment);
    }

    public AppointmentRepository getAppointmentRepository() {
		return appointmentRepository;
	}

	public ClinicRepository getClinicRepository() {
		return clinicRepository;
	}

	public DoctorRepository getDoctorRepository() {
		return doctorRepository;
	}

	public InsuranceServerRepository getInsuranceServerRepository() {
		return insuranceServerRepository;
	}

	public MedicalProcedureRepository getMedicalProcedureRepository() {
		return medicalProcedureRepository;
	}

	public MedicalProcedureTreatmentRepository getMedicalProcedureTreatmentRepository() {
		return medicalProcedureTreatmentRepository;
	}

	public PatientRepository getPatientRepository() {
		return patientRepository;
	}

	public PaymentRepository getPaymentRepository() {
		return paymentRepository;
	}

	public PoliclinicRepository getPoliclinicRepository() {
		return policlinicRepository;
	}

	public StaffRepository getStaffRepository() {
		return staffRepository;
	}

	public TreatmentRepository getTreatmentRepository() {
		return treatmentRepository;
	}

	public void addAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }
}