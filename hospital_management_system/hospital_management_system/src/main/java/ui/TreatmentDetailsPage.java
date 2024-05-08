package ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.example.hospital_management_system.entity.Treatment;
import com.example.hospital_management_system.HospitalManagementService;
import com.example.hospital_management_system.entity.Doctor;
import com.example.hospital_management_system.entity.MedicalProcedure;
import com.example.hospital_management_system.entity.MedicalProcedureTreatment;
import com.example.hospital_management_system.entity.Patient;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

public class TreatmentDetailsPage {
	private JFrame frame;
	private JTable treatment_details_procedures_table;
	private Doctor doctor;
	private Patient patient;
	private Treatment treatment;
	private List<MedicalProcedure> procedures;
	private HospitalManagementService hospitalManagementService;
	private JLabel treatment_details_date_output_label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TreatmentDetailsPage window = new TreatmentDetailsPage(null, null, null, null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TreatmentDetailsPage(Doctor doctor, Treatment treatment, Patient patient,  HospitalManagementService hospitalManagementService) {
		this.doctor = doctor;
		this.treatment = treatment;
		this.patient = patient;
		this.hospitalManagementService = hospitalManagementService;
		this.procedures = getMedicalProceduresByTreatmentId(treatment.getId());
		initialize();
		populateProceduresTable();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 514, 633);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 500, 596);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel treatment_details_title_label = new JLabel("GEÇMİŞ TEDAVİ AYRINTILARI");
		treatment_details_title_label.setHorizontalAlignment(SwingConstants.CENTER);
		treatment_details_title_label.setBounds(10, 10, 480, 13);
		panel.add(treatment_details_title_label);
		
		JLabel treatment_details_patient_id_label = new JLabel("Hasta Kimlik No:");
		treatment_details_patient_id_label.setBounds(37, 44, 97, 13);
		panel.add(treatment_details_patient_id_label);
		
		JLabel treatment_details_doctor_fullname_label = new JLabel("Doktor Ad Soyad:");
		treatment_details_doctor_fullname_label.setBounds(37, 67, 116, 13);
		panel.add(treatment_details_doctor_fullname_label);
		
		JLabel treatment_details_patient_id_output_label = new JLabel(patient.getSocialNumber());
		treatment_details_patient_id_output_label.setBounds(154, 44, 94, 13);
		panel.add(treatment_details_patient_id_output_label);
		
		JLabel treatment_details_doctor_fullname_output_label = new JLabel(doctor.getFirstName() +" "+ doctor.getLastName());
		treatment_details_doctor_fullname_output_label.setBounds(154, 67, 94, 13);
		panel.add(treatment_details_doctor_fullname_output_label);
		
		JLabel treatment_details_date_label = new JLabel("Tarih:");
		treatment_details_date_label.setBounds(272, 44, 44, 13);
		panel.add(treatment_details_date_label);
		
		if (treatment.getTreatmentDate() != null) {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		    String formattedDate = dateFormat.format(treatment.getTreatmentDate());
		    treatment_details_date_output_label = new JLabel(formattedDate);
		} else {
		    treatment_details_date_output_label = new JLabel("Tarih bulunamadı");
		}
		treatment_details_date_output_label.setBounds(326, 44, 129, 13);
		panel.add(treatment_details_date_output_label);
		
		JLabel treatment_details_clinic_label = new JLabel("Klinik:");
		treatment_details_clinic_label.setBounds(272, 67, 44, 13);
		panel.add(treatment_details_clinic_label);
		
		JLabel treatment_details_clinic_output_label = new JLabel(treatment.getClinic().getName());
		treatment_details_clinic_output_label.setBounds(326, 67, 129, 13);
		panel.add(treatment_details_clinic_output_label);
		
		JLabel treatment_details_diagnosis_label = new JLabel("Tanı:");
		treatment_details_diagnosis_label.setBounds(37, 93, 64, 13);
		panel.add(treatment_details_diagnosis_label);
		
		JLabel treatment_details_diagnosis_output_label = new JLabel(treatment.getDiagnosis());
		treatment_details_diagnosis_output_label.setBounds(154, 93, 301, 13);
		panel.add(treatment_details_diagnosis_output_label);
		
		JButton treatment_details_view_prescription_button = new JButton("Reçeteyi Görüntüle");
		treatment_details_view_prescription_button.setBounds(70, 119, 160, 25);
		panel.add(treatment_details_view_prescription_button);
		
	    // Adding an action listener to the button
	    treatment_details_view_prescription_button.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            // Check if the treatment has a prescription
	            if (treatment != null && treatment.getPrescription() != null) {
	                // Display the prescription in a popup dialog
	                JOptionPane.showMessageDialog(frame, treatment.getPrescription(), "Reçete", JOptionPane.INFORMATION_MESSAGE);
	            } else {
	                // Display a message if there is no prescription available
	                JOptionPane.showMessageDialog(frame, "Reçete bulunamadı.", "Reçete", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    });
		
		JButton treatment_details_view_report_button = new JButton("Raporu Görüntüle");
		treatment_details_view_report_button.setBounds(272, 119, 160, 25);
		panel.add(treatment_details_view_report_button);
		
	    // Adding an action listener to the button
	    treatment_details_view_report_button.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            // Check if the treatment has a report
	            if (treatment != null && treatment.getReport() != null) {
	                // Display the report in a popup dialog
	                JOptionPane.showMessageDialog(frame, treatment.getReport(), "Rapor", JOptionPane.INFORMATION_MESSAGE);
	            } else {
	                // Display a message if there is no report available
	                JOptionPane.showMessageDialog(frame, "Rapor bulunamadı.", "Rapor", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    });
		
		JLabel treatment_details_subtitle_label = new JLabel("YAPILAN İŞLEMLER");
		treatment_details_subtitle_label.setHorizontalAlignment(SwingConstants.CENTER);
		treatment_details_subtitle_label.setBounds(10, 196, 480, 13);
		panel.add(treatment_details_subtitle_label);
		
		treatment_details_procedures_table = new JTable(new NonEditableModel(null, 0));
		treatment_details_procedures_table.setBounds(37, 230, 424, 265);
		panel.add(treatment_details_procedures_table);
	}
	
	private List<MedicalProcedure> getMedicalProceduresByTreatmentId(Long treatmentId) {
	    List<MedicalProcedureTreatment> treatments = hospitalManagementService.getMedicalProcedureTreatmentRepository().findListByTreatmentId(treatmentId);
	    return treatments.stream()
	                     .map(MedicalProcedureTreatment::getMedicalProcedure)
	                     .collect(Collectors.toList());
	}
	
	private void populateProceduresTable() {
	    // Column Names for the table
	    String[] columnNames = {"Prosedür Adı"};
	    
	    // Creating the table model and setting the column names
	    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
	    
	    // Adding each procedure name as a row in the model
	    for (MedicalProcedure procedure : procedures) {
	        String[] row = new String[]{procedure.getName()};
	        model.addRow(row);
	    }
	    
	    // Setting the model to the table
	    treatment_details_procedures_table.setModel(model);
	    treatment_details_procedures_table.setRowHeight(20); // Optional, for better row visibility
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
