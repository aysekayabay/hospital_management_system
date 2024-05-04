package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.example.hospital_management_system.entity.Patient;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.DropMode;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;

public class ViewPatientAndAddRecordPage {

	private JFrame frame;
	private JTable view_patient_treatments_table;
	private JTextField add_treatment_diagnosis_textfield;
	private JTable add_treatment_added_procedures_table;
	private JTextField add_treatment_prescription_textfield;
	private JTextField add_treatment_report_textfield;
	private JLabel view_patient_identity_number_output_label = new JLabel("");
	private JLabel view_patient_firstname_output_label = new JLabel("");
	private JLabel view_patient_lastname_output_label = new JLabel("");
	private JLabel view_patient_phone_output_label = new JLabel("");
	private JLabel view_patient_gender_output_label = new JLabel("");
	private JLabel view_patient_birthdate_output_label = new JLabel("");
	private JLabel view_patient_address_output_label = new JLabel("");
	private JLabel view_patient_email_output_label = new JLabel("");
	private final Patient patient;

	private void displayPatientInfo() {
		view_patient_identity_number_output_label.setText(patient.getSocialNumber());
		view_patient_firstname_output_label.setText(patient.getFirstName());
		view_patient_lastname_output_label.setText(patient.getLastName());
		view_patient_phone_output_label.setText(patient.getPhone());
		view_patient_gender_output_label.setText(patient.getGender());
		view_patient_birthdate_output_label.setText(patient.getBirthDate().toString());
		view_patient_address_output_label.setText(patient.getAddress());
		view_patient_email_output_label.setText(patient.getEmail());
	}

	/**
	 * Create the application.
	 */
	public ViewPatientAndAddRecordPage(Patient patient) {
		this.patient = patient;
		initialize();
		displayPatientInfo();
	}

	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 514, 633);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel view_patient_panel = new JPanel();
		view_patient_panel.setBackground(Color.WHITE);
		view_patient_panel.setBounds(0, 0, 500, 596);
		frame.getContentPane().add(view_patient_panel);
		view_patient_panel.setLayout(null);

		JLabel view_patient_title_label = new JLabel("HASTA BİLGİLERİ");
		view_patient_title_label.setHorizontalAlignment(SwingConstants.CENTER);
		view_patient_title_label.setBounds(10, 10, 480, 13);
		view_patient_panel.add(view_patient_title_label);

		view_patient_identity_number_output_label.setBounds(133, 44, 94, 13);
		view_patient_panel.add(view_patient_identity_number_output_label);

		JLabel view_patient_identity_number_label = new JLabel("Kimlik No:");
		view_patient_identity_number_label.setBounds(37, 44, 64, 13);
		view_patient_panel.add(view_patient_identity_number_label);

		JLabel view_patient_firstname_label = new JLabel("Ad:");
		view_patient_firstname_label.setBounds(37, 67, 64, 13);
		view_patient_panel.add(view_patient_firstname_label);

		view_patient_firstname_output_label.setBounds(133, 67, 94, 13);
		view_patient_panel.add(view_patient_firstname_output_label);

		view_patient_phone_output_label.setBounds(133, 113, 94, 13);
		view_patient_panel.add(view_patient_phone_output_label);

		view_patient_lastname_output_label.setBounds(133, 90, 94, 13);
		view_patient_panel.add(view_patient_lastname_output_label);

		JLabel view_patient_lastname_label = new JLabel("Soyad:");
		view_patient_lastname_label.setBounds(37, 90, 64, 13);
		view_patient_panel.add(view_patient_lastname_label);

		JLabel view_patient_phone_label = new JLabel("Telefon:");
		view_patient_phone_label.setBounds(37, 113, 64, 13);
		view_patient_panel.add(view_patient_phone_label);

		JLabel view_patient_gender_label = new JLabel("Cinsiyet:");
		view_patient_gender_label.setBounds(265, 44, 64, 13);
		view_patient_panel.add(view_patient_gender_label);

		JLabel view_patient_birthdate_label = new JLabel("Doğum Tarihi:");
		view_patient_birthdate_label.setBounds(265, 67, 86, 13);
		view_patient_panel.add(view_patient_birthdate_label);

		JLabel view_patient_address_label = new JLabel("Adres:");
		view_patient_address_label.setBounds(265, 90, 64, 13);
		view_patient_panel.add(view_patient_address_label);

		view_patient_gender_output_label.setBounds(361, 44, 94, 13);
		view_patient_panel.add(view_patient_gender_output_label);

		view_patient_birthdate_output_label.setBounds(361, 67, 94, 13);
		view_patient_panel.add(view_patient_birthdate_output_label);

		view_patient_address_output_label.setBounds(361, 90, 94, 13);
		view_patient_panel.add(view_patient_address_output_label);

		JLabel view_patient_email_label = new JLabel("Email:");
		view_patient_email_label.setBounds(37, 136, 64, 13);
		view_patient_panel.add(view_patient_email_label);

		view_patient_email_output_label.setBounds(133, 136, 94, 13);
		view_patient_panel.add(view_patient_email_output_label);

		JLabel view_patient_subtitle_label = new JLabel("TEDAVİ GEÇMİŞİ");
		view_patient_subtitle_label.setHorizontalAlignment(SwingConstants.CENTER);
		view_patient_subtitle_label.setBounds(10, 196, 480, 13);
		view_patient_panel.add(view_patient_subtitle_label);

		view_patient_treatments_table = new JTable();
		view_patient_treatments_table.setBounds(20, 230, 460, 265);
		view_patient_panel.add(view_patient_treatments_table);

		JButton view_patient_view_details_button = new JButton("Tedavi Detaylarını Görüntüle");
		view_patient_view_details_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		view_patient_view_details_button.setBounds(262, 522, 218, 30);
		view_patient_panel.add(view_patient_view_details_button);

		JButton view_patient_add_treatment_button = new JButton("Tedavi Kaydı Ekle");
		view_patient_add_treatment_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		view_patient_add_treatment_button.setBounds(20, 522, 218, 30);
		view_patient_panel.add(view_patient_add_treatment_button);

		JPanel add_treatment_panel = new JPanel();
		add_treatment_panel.setBackground(Color.WHITE);
		add_treatment_panel.setBounds(0, 0, 500, 596);
		frame.getContentPane().add(add_treatment_panel);
		add_treatment_panel.setLayout(null);

		JLabel add_treatment_title_label = new JLabel("TEDAVİ KAYDI EKLE");
		add_treatment_title_label.setHorizontalAlignment(SwingConstants.CENTER);
		add_treatment_title_label.setBounds(10, 10, 480, 13);
		add_treatment_panel.add(add_treatment_title_label);

		JLabel add_treatment_diagnosis_label = new JLabel("Tanı:");
		add_treatment_diagnosis_label.setBounds(37, 44, 64, 13);
		add_treatment_panel.add(add_treatment_diagnosis_label);

		add_treatment_diagnosis_textfield = new JTextField();
		add_treatment_diagnosis_textfield.setBounds(112, 41, 345, 19);
		add_treatment_panel.add(add_treatment_diagnosis_textfield);
		add_treatment_diagnosis_textfield.setColumns(10);

		JLabel add_treatment_procedures_label = new JLabel("İşlemler:");
		add_treatment_procedures_label.setBounds(37, 70, 64, 13);
		add_treatment_panel.add(add_treatment_procedures_label);

		JComboBox add_treatment_procedures_combobox = new JComboBox();
		add_treatment_procedures_combobox.setBackground(Color.WHITE);
		add_treatment_procedures_combobox.setBounds(112, 66, 212, 21);
		add_treatment_panel.add(add_treatment_procedures_combobox);

		JButton add_treatment_add_procedure_button = new JButton("Ekle");
		add_treatment_add_procedure_button.setBounds(334, 66, 123, 21);
		add_treatment_panel.add(add_treatment_add_procedure_button);

		add_treatment_added_procedures_table = new JTable();
		add_treatment_added_procedures_table.setBounds(112, 97, 212, 118);
		add_treatment_panel.add(add_treatment_added_procedures_table);

		JCheckBox add_treatment_prescription_checkbox = new JCheckBox("Reçete");
		add_treatment_prescription_checkbox.setBackground(Color.WHITE);
		add_treatment_prescription_checkbox.setBounds(30, 225, 78, 21);
		add_treatment_panel.add(add_treatment_prescription_checkbox);

		JCheckBox add_treatment_report_checkbox = new JCheckBox("Rapor");
		add_treatment_report_checkbox.setBackground(Color.WHITE);
		add_treatment_report_checkbox.setBounds(28, 336, 78, 21);
		add_treatment_panel.add(add_treatment_report_checkbox);

		add_treatment_prescription_textfield = new JTextField();
		add_treatment_prescription_textfield.setColumns(5);
		add_treatment_prescription_textfield.setBounds(112, 226, 345, 100);
		add_treatment_panel.add(add_treatment_prescription_textfield);

		add_treatment_report_textfield = new JTextField();
		add_treatment_report_textfield.setColumns(10);
		add_treatment_report_textfield.setBounds(112, 337, 345, 100);
		add_treatment_panel.add(add_treatment_report_textfield);

		JButton add_treatment_complete_record_button = new JButton("Kaydı Tamamla");
		add_treatment_complete_record_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add_treatment_complete_record_button.setBounds(262, 522, 218, 30);
		add_treatment_panel.add(add_treatment_complete_record_button);

		JButton add_treatment_cancel_button = new JButton("Vazgeç");
		add_treatment_cancel_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add_treatment_cancel_button.setBackground(Color.WHITE);
		add_treatment_cancel_button.setBounds(20, 522, 218, 30);
		add_treatment_panel.add(add_treatment_cancel_button);
	}
}
