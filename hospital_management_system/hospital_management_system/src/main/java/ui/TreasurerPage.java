package ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.example.hospital_management_system.HospitalManagementService;
import com.example.hospital_management_system.entity.InsuranceServer;
import com.example.hospital_management_system.entity.MedicalProcedure;
import com.example.hospital_management_system.entity.MedicalProcedureTreatment;
import com.example.hospital_management_system.entity.Patient;
import com.example.hospital_management_system.entity.Payment;
import com.example.hospital_management_system.entity.Treatment;

import java.awt.BorderLayout;

public class TreasurerPage {

    private JFrame frame;

    private JLabel title;

    private JLabel patient_social_no_label;
    private JTextField patient_social_no_textField;

    private JLabel patient_insurance_label;
    private JTextField patient_insurance_textField;

    private JLabel total_price_label;
    private JTextField total_price_textField;

    private JLabel type_of_pay_label;
    private JComboBox<String> type_of_pay_combo;

    private JButton calculate_price_button;
    private JButton confirm_payment_button;

	private HospitalManagementService hospitalManagementService;
	private InsuranceServer insuranceServer;
	private Treatment current_treatment;
	
	private String social_no;
    /**
     * Launch the application.
     */
    
    private void showErrorMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Hata", JOptionPane.ERROR_MESSAGE);    	
    }
    
    protected void calculate_price_function() {
        this.social_no = patient_social_no_textField.getText();
        if (this.social_no.isBlank()) {
            showErrorMessage("Hasta Kimlik Numarası Boş olamaz");
            current_treatment = null;
            return;
        }
        
        if (this.social_no.length() != 11) {
            showErrorMessage("Hasta Kimlik Numarası 11 haneli olmalıdır");
            current_treatment = null;
            return;
        }
        
        List<Treatment> treatments = hospitalManagementService.getTreatmentRepository().findByPatientSocialNumber(social_no);
        if (treatments.isEmpty()) {
            showErrorMessage("Hasta numarasına göre tedavi bulunamadı.");
            current_treatment = null;
            return;
        }

        boolean hasInsurance = false;

		for (String person : insuranceServer.getPeopleWithInsurance()) {
		    if (person.equals(this.social_no)) {
		        hasInsurance = true;
		        break;
		    }
		}
		
        patient_insurance_textField.setText(hasInsurance ? "Evet" : "Hayır");

        double totalCost = 0;

        for (Treatment treatment : treatments) {
            if (treatment.getPayment() == null) {
            	current_treatment = treatment;
            	break;
            }
        }
        
        if (current_treatment != null) {
        	Long treatment_id = current_treatment.getId();
        	System.out.println(treatment_id);
            List<MedicalProcedureTreatment> procedureTreatment = hospitalManagementService.getMedicalProcedureTreatmentRepository().findByTreatmentId(treatment_id);
            if (procedureTreatment != null) {
                for (MedicalProcedureTreatment currentProcedureTreatment : procedureTreatment) {
                	MedicalProcedure procedure = currentProcedureTreatment.getMedicalProcedure();
                	
                    if (procedure != null) {
                        totalCost += procedure.getCost();
                    } else {
                    	System.out.println("NULL GELDİ");
                    }
                }
            	
            }
        }
        
        total_price_textField.setText("0");
        if (totalCost > 0) {
        	total_price_textField.setText(String.valueOf(totalCost));
            type_of_pay_combo.setEditable(true);
        	confirm_payment_button.setEnabled(true);
        }
    }



    protected void confirm_payment_function() {
        Patient patient = hospitalManagementService.getPatientRepository().findBySocialNumber(this.social_no);
        if (patient == null) {
            showErrorMessage("Veritabanında bu kimlik numarasına sahip hasta bulunamadı.");
            return;
        }

        String paymentMethod = (String) type_of_pay_combo.getSelectedItem();
        double amount = Double.parseDouble(total_price_textField.getText());
        Timestamp paymentDate = new Timestamp(System.currentTimeMillis());

        Payment newPayment = new Payment();
        newPayment.setPatient(patient);
        newPayment.setPaymentMethod(paymentMethod);
        newPayment.setAmount(amount);
        newPayment.setPaymentDate(paymentDate);

        hospitalManagementService.getPaymentRepository().save(newPayment);
        
		current_treatment.setPayment(newPayment);
		
		hospitalManagementService.getTreatmentRepository().save(current_treatment);

        JOptionPane.showMessageDialog(null, "Ödeme Onaylandı", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
    }


    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TreasurerPage window = new TreasurerPage(null);
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
    public TreasurerPage(HospitalManagementService hospitalManagementService) {
		this.hospitalManagementService = hospitalManagementService;
		this.insuranceServer = hospitalManagementService.getInsuranceServerRepository().findById((long) 1).orElse(null);
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

        title = new JLabel("MUAYENE ÜCRETİ ÖDEME");
        title.setFont(new Font("Tahoma", Font.BOLD, 16));
        title.setBounds(161, 20, 229, 20);
        panel.add(title);

        patient_social_no_label = new JLabel("Hasta Kimlik No:");
        patient_social_no_label.setBounds(37, 96, 150, 20);
        panel.add(patient_social_no_label);

        patient_social_no_textField = new JTextField();
        patient_social_no_textField.setBounds(178, 97, 150, 20);
        panel.add(patient_social_no_textField);
        
        patient_insurance_label = new JLabel("Sigorta Durumu:");
        patient_insurance_label.setBounds(37, 203, 150, 20);
        panel.add(patient_insurance_label);

        patient_insurance_textField = new JTextField();
        patient_insurance_textField.setBounds(178, 204, 150, 20);
        panel.add(patient_insurance_textField);
        patient_insurance_textField.setEditable(false);

        total_price_label = new JLabel("Toplam Ücret:");
        total_price_label.setBounds(37, 252, 150, 20);
        panel.add(total_price_label);

        total_price_textField = new JTextField();
        total_price_textField.setBounds(178, 253, 150, 20);
        panel.add(total_price_textField);
        total_price_textField.setEditable(false);

        type_of_pay_label = new JLabel("Ödeme Türü:");
        type_of_pay_label.setBounds(37, 298, 150, 20);
        panel.add(type_of_pay_label);

        String[] paymentTypes = {"Nakit", "Kredi Kartı"};
        type_of_pay_combo = new JComboBox<>(paymentTypes);
        type_of_pay_combo.setBounds(178, 298, 150, 20);
        panel.add(type_of_pay_combo);
        type_of_pay_combo.setEditable(false);

        calculate_price_button = new JButton("Ücreti Hesapla");
        calculate_price_button.setBounds(363, 96, 133, 20);
        panel.add(calculate_price_button);
        calculate_price_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculate_price_function();
			}
		});

        confirm_payment_button = new JButton("Ödemeyi Onayla");
        confirm_payment_button.setBounds(178, 376, 150, 30);
        panel.add(confirm_payment_button);
        confirm_payment_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirm_payment_function();
			}
		});
        confirm_payment_button.setEnabled(false);
    }

    public JFrame getFrame() {
		return frame;
	}
}
