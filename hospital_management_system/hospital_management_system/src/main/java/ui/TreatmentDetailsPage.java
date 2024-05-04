package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

public class TreatmentDetailsPage {

	private JFrame frame;
	private JTable treatment_details_procedures_table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TreatmentDetailsPage window = new TreatmentDetailsPage();
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
	public TreatmentDetailsPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 514, 633);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		JLabel treatment_details_patient_id_output_label = new JLabel("");
		treatment_details_patient_id_output_label.setBounds(154, 44, 94, 13);
		panel.add(treatment_details_patient_id_output_label);
		
		JLabel treatment_details_doctor_fullname_output_label = new JLabel("");
		treatment_details_doctor_fullname_output_label.setBounds(154, 67, 94, 13);
		panel.add(treatment_details_doctor_fullname_output_label);
		
		JLabel treatment_details_date_label = new JLabel("Tarih:");
		treatment_details_date_label.setBounds(272, 44, 44, 13);
		panel.add(treatment_details_date_label);
		
		JLabel treatment_details_date_output_label = new JLabel("");
		treatment_details_date_output_label.setBounds(326, 44, 129, 13);
		panel.add(treatment_details_date_output_label);
		
		JLabel treatment_details_clinic_label = new JLabel("Klinik:");
		treatment_details_clinic_label.setBounds(272, 67, 44, 13);
		panel.add(treatment_details_clinic_label);
		
		JLabel treatment_details_clinic_output_label = new JLabel("");
		treatment_details_clinic_output_label.setBounds(326, 67, 129, 13);
		panel.add(treatment_details_clinic_output_label);
		
		JLabel treatment_details_diagnosis_label = new JLabel("Tanı:");
		treatment_details_diagnosis_label.setBounds(37, 93, 64, 13);
		panel.add(treatment_details_diagnosis_label);
		
		JButton treatment_details_view_prescription_button = new JButton("Reçeteyi Görüntüle");
		treatment_details_view_prescription_button.setBounds(70, 119, 160, 25);
		panel.add(treatment_details_view_prescription_button);
		
		JButton treatment_details_view_report_label = new JButton("Raporu Görüntüle");
		treatment_details_view_report_label.setBounds(272, 119, 160, 25);
		panel.add(treatment_details_view_report_label);
		
		JLabel treatment_details_subtitle_label = new JLabel("YAPILAN İŞLEMLER");
		treatment_details_subtitle_label.setHorizontalAlignment(SwingConstants.CENTER);
		treatment_details_subtitle_label.setBounds(10, 196, 480, 13);
		panel.add(treatment_details_subtitle_label);
		
		treatment_details_procedures_table = new JTable();
		treatment_details_procedures_table.setBounds(37, 230, 424, 265);
		panel.add(treatment_details_procedures_table);
		
		JLabel treatment_details_diagnosis_output_label = new JLabel("");
		treatment_details_diagnosis_output_label.setBounds(154, 93, 301, 13);
		panel.add(treatment_details_diagnosis_output_label);
	}
}
