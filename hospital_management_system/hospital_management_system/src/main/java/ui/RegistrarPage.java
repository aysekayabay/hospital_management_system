package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.example.hospital_management_system.HospitalManagementService;
import com.example.hospital_management_system.entity.Patient;

public class RegistrarPage {

	private JFrame frame;

	private JLabel title;

    private JLabel patient_social_no_label;
    private JTextField patient_social_no_textField;

    private JLabel patient_name_label;
    private JTextField patient_name_textField;

    private JLabel patient_surname_label;
    private JTextField patient_surname_textField;

    private JLabel patient_telno_label;
    private JTextField patient_telno_textField;

    private JLabel patient_email_label;
    private JTextField patient_email_textField;
    
    private JLabel patient_sex_label;
    private JComboBox<String> patient_sex_comboBox;
    
    private JLabel patient_birthdate_label;
    private JTextField patient_birthdate_textField;

    private JLabel patient_address_label;
    private JTextField patient_address_textField;
    
    private JButton add_patient_button;

	private HospitalManagementService hospitalManagementService;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrarPage window = new RegistrarPage(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void showErrorMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Hata", JOptionPane.ERROR_MESSAGE);    	
    }
	
	private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
	
	private void add_patient_function() {
	    String socialNo = patient_social_no_textField.getText();
	    String name = patient_name_textField.getText();
	    String surName = patient_surname_textField.getText();
	    String telNo = patient_telno_textField.getText();
	    String email = patient_email_textField.getText();
	    String sex = (String) patient_sex_comboBox.getSelectedItem();
	    String address = patient_address_textField.getText();
	    String birthdate = patient_birthdate_textField.getText();
	    
	    if (socialNo.isEmpty() || name.isEmpty() || surName.isEmpty() || telNo.isEmpty() || email.isEmpty() || sex.isEmpty() || address.isEmpty() || birthdate.isEmpty()) {
	        showErrorMessage("Tüm alanlar doldurulmalıdır!");
	        return;
	    }
	    
	    if(socialNo.length() != 11 && isNumeric(socialNo)){
	        showErrorMessage("Kimlik numarası 11 haneli sayılardan oluşmalıdır!");
	        return;
	    }
	    if(telNo.length() != 10 && isNumeric(telNo)){
	        showErrorMessage("Telefon numarası 10 haneli sayılardan oluşmalıdır!");
	        return;
	    }
	    if(birthdate.length() != 10){
	        showErrorMessage("Doğum tarihi 10 haneli olmalıdır!");
	        return;
	    }
	    
	    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
	    Date utilDate;
	    try {
	        utilDate = formatter.parse(birthdate);
	    } catch (ParseException e) {
	        showErrorMessage("Doğum tarihi formatı dd.MM.yyyy olmalıdır!");
	        return;
	    }

	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

	    Patient existingPatient = hospitalManagementService.getPatientRepository().findBySocialNumber(socialNo);
	    if(existingPatient != null){
	        showErrorMessage("Bu kimlik numarasına sahip bir hasta zaten mevcut!");
	        return;
	    }

	    Patient patient = new Patient(socialNo, name, surName, sex, address, sqlDate, email, telNo);
	    hospitalManagementService.getPatientRepository().save(patient);

        JOptionPane.showMessageDialog(null, "Hasta Kaydı Eklendi.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Create the application.
	 */
	public RegistrarPage(HospitalManagementService hospitalManagementService) {
		this.hospitalManagementService = hospitalManagementService;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 532, 612);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        title = new JLabel("HASTA KAYIT");
        title.setFont(new Font("Tahoma", Font.BOLD, 16));
        title.setBounds(205, 20, 229, 20);
        panel.add(title);

        patient_social_no_label = new JLabel("Kimlik No:");
        patient_social_no_label.setBounds(37, 96, 100, 20);
        panel.add(patient_social_no_label);

        patient_social_no_textField = new JTextField();
        patient_social_no_textField.setBounds(135, 97, 100, 20);
        panel.add(patient_social_no_textField);

        patient_name_label = new JLabel("Ad:");
        patient_name_label.setBounds(37, 125, 100, 20);
        panel.add(patient_name_label);

        patient_name_textField = new JTextField();
        patient_name_textField.setBounds(135, 126, 100, 20);
        panel.add(patient_name_textField);

        patient_surname_label = new JLabel("Soy Ad:");
        patient_surname_label.setBounds(37, 154, 100, 20);
        panel.add(patient_surname_label);

        patient_surname_textField = new JTextField();
        patient_surname_textField.setBounds(135, 155, 100, 20);
        panel.add(patient_surname_textField);

        patient_telno_label = new JLabel("Telefon:");
        patient_telno_label.setBounds(255, 154, 100, 20);
        panel.add(patient_telno_label);

        patient_telno_textField = new JTextField();
        patient_telno_textField.setBounds(353, 155, 100, 20);
        panel.add(patient_telno_textField);
        
        patient_email_label = new JLabel("Email:");
        patient_email_label.setBounds(37, 183, 100, 20);
        panel.add(patient_email_label);

        patient_email_textField = new JTextField();
        patient_email_textField.setBounds(135, 184, 318, 20);
        panel.add(patient_email_textField);

        patient_sex_label = new JLabel("Cinsiyet:");
        patient_sex_label.setBounds(255, 96, 100, 20);
        panel.add(patient_sex_label);

        String[] sexTypes = {"Kadın", "Erkek", "Diğer"};
        patient_sex_comboBox = new JComboBox<>(sexTypes);
        patient_sex_comboBox.setBounds(353, 97, 100, 20);
        panel.add(patient_sex_comboBox);

        patient_birthdate_label = new JLabel("Doğum Tarihi:");
        patient_birthdate_label.setBounds(255, 125, 100, 20);
        panel.add(patient_birthdate_label);

        patient_birthdate_textField = new JTextField();
        patient_birthdate_textField.setBounds(353, 126, 100, 20);
        panel.add(patient_birthdate_textField);
        
        patient_address_label = new JLabel("Adres:");
        patient_address_label.setBounds(37, 213, 100, 20);
        panel.add(patient_address_label);

        patient_address_textField = new JTextField();
        patient_address_textField.setBounds(135, 213, 318, 20);
        panel.add(patient_address_textField);
        
        // Onayla butonu
        add_patient_button = new JButton("Kaydı Ekle");
        add_patient_button.setBounds(178, 376, 150, 30);
        panel.add(add_patient_button);
        add_patient_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add_patient_function();
			}
		});
	}
	
	
	public JFrame getFrame() {
		return frame;
	}
}
