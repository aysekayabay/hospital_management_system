package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.example.hospital_management_system.Constants;
import com.example.hospital_management_system.HospitalManagementService;
import com.example.hospital_management_system.entity.Appointment;
import com.example.hospital_management_system.entity.Doctor;
import com.example.hospital_management_system.entity.MedicalProcedure;
import com.example.hospital_management_system.entity.MedicalProcedureTreatment;
import com.example.hospital_management_system.entity.Patient;
import com.example.hospital_management_system.entity.Treatment;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ViewPatientAndAddRecordPage {

	private JFrame frame;
	private JTable view_patient_treatments_table;
	private JTextField add_treatment_diagnosis_textfield;
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
	private final Doctor doctor;
	private final Appointment appointment;
	private final HospitalManagementService hospitalManagementService;
	private List<Treatment> treatments;
	private JComboBox<String> add_treatment_procedures_combobox = new JComboBox<String>();
	private DefaultTableModel add_treatment_added_procedures_model;
	private DefaultTableModel view_patient_treatment_history_model;
	private JTable add_treatment_added_procedures_table;
	private JButton add_treatment_add_procedure_button;
	private JButton view_patient_add_treatment_button;
	private JLabel view_patient_print_prescription_label;
	private JLabel view_patient_print_report_label;
	private Treatment docsTreatment;

	private void displayPatientInfo() {
		view_patient_identity_number_output_label.setText(patient.getSocialNumber());
		view_patient_firstname_output_label.setText(patient.getFirstName());
		view_patient_lastname_output_label.setText(patient.getLastName());
		view_patient_phone_output_label.setText(patient.getPhone());
		view_patient_gender_output_label.setText(patient.getGender());
		Date birthDate = patient.getBirthDate();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String formattedBirthDate = formatter.format(birthDate);
		view_patient_birthdate_output_label.setText(formattedBirthDate);
		view_patient_birthdate_output_label.setText(patient.getBirthDate().toString());
		view_patient_address_output_label.setText(patient.getAddress());
		view_patient_email_output_label.setText(patient.getEmail());

		loadPatientTreatmentHistory();
	}

	/**
	 * Create the application.
	 */
	public ViewPatientAndAddRecordPage(Patient patient, Doctor doctor, Appointment appointment,
			HospitalManagementService hospitalManagementService) {
		this.hospitalManagementService = hospitalManagementService;
		this.doctor = doctor;
		this.appointment = appointment;
		this.patient = patient;
		this.treatments = hospitalManagementService.getTreatmentRepository()
				.findByPatientSocialNumberOrderByTreatmentDateDesc(patient.getSocialNumber());
		initialize();
		displayPatientInfo();
		loadProceduresComboBox();
	}

	public JFrame getFrame() {
		return frame;
	}

	private void loadProceduresComboBox() {
		List<MedicalProcedure> procedures = hospitalManagementService.getMedicalProcedureRepository()
				.findByPoliclinicId(Constants.hospitalId);
		for (MedicalProcedure procedure : procedures) {
			add_treatment_procedures_combobox.addItem(procedure.getName());
		}
	}

	private void loadPatientTreatmentHistory() {
		this.treatments = hospitalManagementService.getTreatmentRepository()
				.findByPatientSocialNumberOrderByTreatmentDateDesc(patient.getSocialNumber());
		view_patient_treatment_history_model.setRowCount(0);

		for (Treatment treatment : treatments) {
			if (treatment.getAppointment().getId() == appointment.getId()) {
				// zaten buna tedavi eklendi
				view_patient_add_treatment_button.setVisible(false);
				view_patient_add_treatment_button.setVisible(false);
				view_patient_print_prescription_label.setVisible(true);
				view_patient_print_report_label.setVisible(true);
				docsTreatment = treatment;
			}

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			String formattedAppointmentDate = dateFormat.format(treatment.getAppointment().getAppointmentDate());
			view_patient_treatment_history_model
					.addRow(new Object[] { formattedAppointmentDate, treatment.getClinic().getName(),
							treatment.getDoctorID().getFirstName() + " "+treatment.getDoctorID().getLastName(),
							treatment.getDiagnosis() });
		}
	}

	private void addProcedureToTable() {
		String selectedProcedure = (String) add_treatment_procedures_combobox.getSelectedItem();
		add_treatment_added_procedures_model.addRow(new Object[] { selectedProcedure });
	}

	private void removeProcedureFromTable() {
		int selectedRow = add_treatment_added_procedures_table.getSelectedRow();
		if (selectedRow != -1) {
			add_treatment_added_procedures_model.removeRow(selectedRow);
		}
	}
	
	public static boolean testReportContent(String report) {
		boolean containsNumber = false;
        boolean containsDay = false;
		for (char c : report.toCharArray()) {
            if (Character.isDigit(c)) {
                containsNumber = true;
            }
            if (c == 'g' || c == 'G' || c == 'ğ' || c == 'Ğ') {
                if (report.indexOf("gün") != -1 || report.indexOf("Gün") != -1 || report.indexOf("GÜN") != -1) {
                    containsDay = true;
                    break;
                }
            }
        }
		boolean result = containsDay && containsNumber;
		return result;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 514, 633);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

		view_patient_email_output_label.setBounds(133, 136, 129, 13);
		view_patient_panel.add(view_patient_email_output_label);

		JLabel view_patient_subtitle_label = new JLabel("TEDAVİ GEÇMİŞİ");
		view_patient_subtitle_label.setHorizontalAlignment(SwingConstants.CENTER);
		view_patient_subtitle_label.setBounds(10, 196, 480, 13);
		view_patient_panel.add(view_patient_subtitle_label);

		JButton view_patient_view_details_button = new JButton("Tedavi Detaylarını Görüntüle");
		view_patient_view_details_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = view_patient_treatments_table.getSelectedRow();
				if (selectedRow != -1) {
					// Assuming `treatments` is accessible here, and its indices are aligned with
					// table rows
					Treatment selectedTreatment = treatments.get(selectedRow);
					if (selectedTreatment != null) {
						// Code to display treatment details
						TreatmentDetailsPage window = new TreatmentDetailsPage(doctor, selectedTreatment, patient,
								hospitalManagementService);
						window.getFrame().setVisible(true);
					} else {
						JOptionPane.showMessageDialog(frame, "Tedavi Detayları Bulunamadı", "Hata",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(frame,
							"Lütfen Detaylarını Görüntülemek İstediğiniz Tedaviyi Seçiniz.", "Seçim Yok",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		view_patient_view_details_button.setBounds(262, 522, 218, 30);
		view_patient_panel.add(view_patient_view_details_button);

		view_patient_treatment_history_model = new DefaultTableModel(new Object[][] {},
				new String[] { "Tarih", "Klinik", "Doktor", "Tanı" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JScrollPane view_patient_treatment_history_scrollpane = new JScrollPane();
		view_patient_treatment_history_scrollpane.setBounds(20, 230, 460, 265);
		view_patient_panel.add(view_patient_treatment_history_scrollpane);

		view_patient_treatments_table = new JTable(view_patient_treatment_history_model);
		view_patient_treatment_history_scrollpane.setViewportView(view_patient_treatments_table);

		JPanel add_treatment_panel = new JPanel();
		add_treatment_panel.setBackground(Color.WHITE);
		add_treatment_panel.setBounds(0, 0, 500, 596);
		frame.getContentPane().add(add_treatment_panel);
		add_treatment_panel.setLayout(null);
		add_treatment_panel.setVisible(false);

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

		add_treatment_procedures_combobox.setBackground(Color.WHITE);
		add_treatment_procedures_combobox.setBounds(112, 66, 212, 21);
		add_treatment_panel.add(add_treatment_procedures_combobox);

		add_treatment_add_procedure_button = new JButton("Ekle");
		add_treatment_add_procedure_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addProcedureToTable();
			}
		});
		add_treatment_add_procedure_button.setBounds(334, 66, 123, 21);
		add_treatment_panel.add(add_treatment_add_procedure_button);

		add_treatment_added_procedures_model = new DefaultTableModel(new Object[][] {},
				new String[] { "Eklenen İşlemler" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		// Combobox başlangıç öğesi ekleniyor
		add_treatment_procedures_combobox.addItem("Seçiniz");
		add_treatment_procedures_combobox.setSelectedIndex(0);

		// Ekle butonunun durumu kontrol ediliyor
		add_treatment_add_procedure_button.setEnabled(false);

		// Combobox seçim durumu dinleniyor
		add_treatment_procedures_combobox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String selectedProcedure = (String) add_treatment_procedures_combobox.getSelectedItem();
					// Seçili prosedür "Seçiniz" değilse ekle butonunu aktif hale getir
					add_treatment_add_procedure_button.setEnabled(!selectedProcedure.equals("Seçiniz"));
				}
			}
		});

		JCheckBox add_treatment_prescription_checkbox = new JCheckBox("Reçete");
		add_treatment_prescription_checkbox.setBackground(Color.WHITE);
		add_treatment_prescription_checkbox.setBounds(30, 225, 78, 21);
		add_treatment_panel.add(add_treatment_prescription_checkbox);

		JCheckBox add_treatment_report_checkbox = new JCheckBox("Rapor");
		add_treatment_report_checkbox.setBackground(Color.WHITE);
		add_treatment_report_checkbox.setBounds(28, 336, 78, 21);
		add_treatment_panel.add(add_treatment_report_checkbox);

		add_treatment_prescription_checkbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add_treatment_prescription_textfield.setEnabled(add_treatment_prescription_checkbox.isSelected());
			}
		});

		add_treatment_report_checkbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add_treatment_report_textfield.setEnabled(add_treatment_report_checkbox.isSelected());
			}
		});
		add_treatment_prescription_textfield = new JTextField();
		add_treatment_prescription_textfield.setColumns(5);
		add_treatment_prescription_textfield.setBounds(112, 226, 345, 100);
		add_treatment_panel.add(add_treatment_prescription_textfield);

		add_treatment_report_textfield = new JTextField();
		add_treatment_report_textfield.setColumns(10);
		add_treatment_report_textfield.setBounds(112, 337, 345, 100);
		add_treatment_panel.add(add_treatment_report_textfield);

		add_treatment_prescription_textfield.setEnabled(add_treatment_prescription_checkbox.isSelected());
		add_treatment_report_textfield.setEnabled(add_treatment_report_checkbox.isSelected());

		JButton add_treatment_cancel_button = new JButton("Vazgeç");
		add_treatment_cancel_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view_patient_panel.setVisible(true);
				add_treatment_panel.setVisible(false);
			}
		});
		add_treatment_cancel_button.setBackground(Color.WHITE);
		add_treatment_cancel_button.setBounds(20, 522, 218, 30);
		add_treatment_panel.add(add_treatment_cancel_button);

		JButton add_treatment_remove_procedure_button = new JButton("Kaldır");
		add_treatment_remove_procedure_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeProcedureFromTable();
			}
		});
		add_treatment_remove_procedure_button.setEnabled(false);
		add_treatment_remove_procedure_button.setBounds(334, 97, 123, 21);
		add_treatment_panel.add(add_treatment_remove_procedure_button);

		add_treatment_added_procedures_table = new JTable(add_treatment_added_procedures_model);

		JScrollPane add_treatment_added_procedures_scrollpane = new JScrollPane(add_treatment_added_procedures_table);
		add_treatment_added_procedures_scrollpane.setBounds(112, 97, 212, 119);
		add_treatment_panel.add(add_treatment_added_procedures_scrollpane);

		ListSelectionModel selectionModel = add_treatment_added_procedures_table.getSelectionModel();
		selectionModel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				// Seçili satırın dizinini al
				int selectedRow = add_treatment_added_procedures_table.getSelectedRow();
				// Eğer bir satır seçiliyse
				if (selectedRow != -1) {
					// Kaldır butonunu aktif hale getir
					add_treatment_remove_procedure_button.setEnabled(true);
				} else {
					// Eğer seçili bir satır yoksa, kaldır butonunu devre dışı bırak
					add_treatment_remove_procedure_button.setEnabled(false);
				}
			}
		});
		
		view_patient_print_prescription_label = new JLabel("Reçeteyi İndir");
		view_patient_print_prescription_label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Reçete dosyasının oluşturulacağı dizin
		        String directory = "./";
		        // Dosyanın adı
		        String fileName = "recete.txt";
		        // Reçete içeriği
		        String prescriptionContent = "HASTA BİLGİLERİ\n"
		                + "Kimlik No: " + patient.getSocialNumber() + "\n"
		                + "Ad: " + patient.getFirstName() + "\n"
		                + "Soyad: " + patient.getLastName() + "\n"
		                + "Telefon: " + patient.getPhone() + "\n"
		                + "Cinsiyet: " + patient.getGender() + "\n"
		                + "Doğum Tarihi: " + view_patient_birthdate_output_label.getText() + "\n"
		                + "Adres: " + patient.getAddress() + "\n"
		                + "Email: " + patient.getEmail() + "\n\n"
		                + "REÇETE BİLGİLERİ\n"
		                + "Tarih: " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new java.util.Date()) + "\n"
		                + "Doktor: " + doctor.getFirstName() + " " + doctor.getLastName() + "\n"
		                + "Reçete: " + docsTreatment.getPrescription() + "\n";
		        try {
		            // Dosya yazma işlemi için PrintWriter oluştur
		            PrintWriter writer = new PrintWriter(directory + fileName);
		            // Reçete içeriğini dosyaya yaz
		            writer.println(prescriptionContent);
		            // PrintWriter'ı kapat
		            writer.close();

		            // Başarılı bir şekilde dosya oluşturulduğunda bir mesaj göster
		            JOptionPane.showMessageDialog(frame, "Reçete başarıyla oluşturuldu.");

		        } catch (FileNotFoundException ex) {
		            // Dosya oluşturulurken bir hata oluştuğunda bir hata mesajı göster
		            JOptionPane.showMessageDialog(frame, "Dosya oluşturulurken bir hata oluştu.", "Hata",
		                    JOptionPane.ERROR_MESSAGE);
		            ex.printStackTrace();
		        }
			}
		});
		view_patient_print_prescription_label.setForeground(SystemColor.textHighlight);
		view_patient_print_prescription_label.setBounds(144, 562, 94, 13);
		view_patient_panel.add(view_patient_print_prescription_label);
		
		view_patient_print_report_label = new JLabel("Raporu İndir");
		view_patient_print_report_label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Rapor dosyasının oluşturulacağı dizin
		        String directory = "./";
		        // Dosyanın adı
		        String fileName = "rapor.txt";
		        // Rapor içeriği
		        String prescriptionContent = "HASTA BİLGİLERİ\n"
		                + "Kimlik No: " + patient.getSocialNumber() + "\n"
		                + "Ad: " + patient.getFirstName() + "\n"
		                + "Soyad: " + patient.getLastName() + "\n"
		                + "Telefon: " + patient.getPhone() + "\n"
		                + "Cinsiyet: " + patient.getGender() + "\n"
		                + "Doğum Tarihi: " + view_patient_birthdate_output_label.getText() + "\n"
		                + "Adres: " + patient.getAddress() + "\n"
		                + "Email: " + patient.getEmail() + "\n\n"
		                + "RAPOR BİLGİLERİ\n"
		                + "Tarih: " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new java.util.Date()) + "\n"
		                + "Doktor: " + doctor.getFirstName() + " " + doctor.getLastName() + "\n"
		                + "Rapor: " + docsTreatment.getReport() + "\n";
		        try {
		            // Dosya yazma işlemi için PrintWriter oluştur
		            PrintWriter writer = new PrintWriter(directory + fileName);
		            // Reçete içeriğini dosyaya yaz
		            writer.println(prescriptionContent);
		            // PrintWriter'ı kapat
		            writer.close();

		            // Başarılı bir şekilde dosya oluşturulduğunda bir mesaj göster
		            JOptionPane.showMessageDialog(frame, "Rapor başarıyla oluşturuldu.");

		        } catch (FileNotFoundException ex) {
		            // Dosya oluşturulurken bir hata oluştuğunda bir hata mesajı göster
		            JOptionPane.showMessageDialog(frame, "Dosya oluşturulurken bir hata oluştu.", "Hata",
		                    JOptionPane.ERROR_MESSAGE);
		            ex.printStackTrace();
		        }
			}
		});
		view_patient_print_report_label.setForeground(SystemColor.textHighlight);
		view_patient_print_report_label.setBounds(20, 562, 94, 13);
		view_patient_panel.add(view_patient_print_report_label);

			
		view_patient_print_prescription_label.setVisible(false);
		view_patient_print_report_label.setVisible(false);
		
		view_patient_add_treatment_button = new JButton("Tedavi Kaydı Ekle");
		view_patient_add_treatment_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view_patient_panel.setVisible(false);
				add_treatment_panel.setVisible(true);
			}
		});
		view_patient_add_treatment_button.setBounds(20, 522, 218, 30);
		view_patient_panel.add(view_patient_add_treatment_button);

		JButton add_treatment_complete_record_button = new JButton("Kaydı Tamamla");
		add_treatment_complete_record_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String diagnosis = add_treatment_diagnosis_textfield.getText();
				String prescription = add_treatment_prescription_textfield.getText();
				String report = add_treatment_report_textfield.getText();

				// Treatment objesini oluştur
				Treatment treatment = new Treatment(diagnosis, Timestamp.from(Instant.now()), doctor.getClinic(),
						doctor, null, appointment, patient);

				// Reçete checkbox'ı işaretliyse
				if (add_treatment_prescription_checkbox.isSelected()) {
					treatment.setPrescription(prescription);
				}
				boolean reportResult = true;
				// Rapor checkbox'ı işaretliyse
				if (add_treatment_report_checkbox.isSelected()) {
					treatment.setReport(report);
					reportResult = testReportContent(report);
				}
				
				
				if (reportResult) {
					// Tedaviyi kaydet
					Treatment savedTreatment = hospitalManagementService.getTreatmentRepository().save(treatment);

					if (savedTreatment != null) {
						// Başarılı bir şekilde tedavi kaydedildiğinde bir mesaj göster
						JOptionPane.showMessageDialog(frame, "Tedavi kaydı başarıyla oluşturuldu.");

						// Seçilen prosedürlerin ID'lerini al ve tedaviye ekle
						for (int i = 0; i < add_treatment_added_procedures_model.getRowCount(); i++) {
							String procedureName = (String) add_treatment_added_procedures_model.getValueAt(i, 0);
							MedicalProcedure procedure = hospitalManagementService.getMedicalProcedureRepository()
									.findByName(procedureName);

							if (procedure != null) {
								// MedicalProcedureTreatment nesnesi oluştur
								MedicalProcedureTreatment medicalProcedureTreatment = new MedicalProcedureTreatment(
										procedure, savedTreatment);
								hospitalManagementService.getMedicalProcedureTreatmentRepository()
										.save(medicalProcedureTreatment);
							}
						}
						loadPatientTreatmentHistory();
						add_treatment_panel.setVisible(false);
						view_patient_panel.setVisible(true);
						view_patient_add_treatment_button.setVisible(false);
					} else {
						// Tedavi kaydedilirken bir hata oluştuğunda bir hata mesajı göster
						JOptionPane.showMessageDialog(frame, "Tedavi kaydı oluşturulurken bir hata oluştu.", "Hata",
								JOptionPane.ERROR_MESSAGE);
					}
					// İlgili alanları temizle

					add_treatment_diagnosis_textfield.setText("");
					add_treatment_prescription_textfield.setText("");
					add_treatment_report_textfield.setText("");
				}
				else {
					JOptionPane.showMessageDialog(frame, "Rapor içeriği 'gün' ve sayı değerlerini içermelidir.", "Hata",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		add_treatment_complete_record_button.setBounds(262, 522, 218, 30);
		add_treatment_panel.add(add_treatment_complete_record_button);

	}
}