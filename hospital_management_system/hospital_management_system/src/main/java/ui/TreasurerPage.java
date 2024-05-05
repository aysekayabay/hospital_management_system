package ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.example.hospital_management_system.HospitalManagementService;
import com.example.hospital_management_system.entity.Doctor;
import com.example.hospital_management_system.entity.InsuranceServer;
import com.example.hospital_management_system.entity.MedicalProcedure;
import com.example.hospital_management_system.entity.MedicalProcedureTreatment;
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
    /**
     * Launch the application.
     */
    
    private void showErrorMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Hata", JOptionPane.ERROR_MESSAGE);    	
    }
    
    protected void calculate_price_function() {
        String social_no = patient_social_no_textField.getText();
        if (social_no.isEmpty()) {
            showErrorMessage("Hasta Kimlik Numarası Boş olamaz");
            return; // Hasta kimlik numarası boşsa işlemi sonlandır
        }

        // Hasta numarasına göre tedavileri bul
        List<Treatment> treatments = hospitalManagementService.getTreatmentRepository().findByPatientSocialNo(social_no);
        if (treatments.isEmpty()) {
            showErrorMessage("Hasta numarasına göre tedavi bulunamadı.");
            return;
        }

        boolean hasInsurance = false;

        // Sigortalı hastalar listesinde hastanın olup olmadığını kontrol et
		for (String person : insuranceServer.getPeopleWithInsurance()) {
		    if (person.equals(social_no)) {
		        hasInsurance = true;
		        break;
		    }
		}
		
        // Hastanın sigortası olup olmadığına göre text alanını güncelle
        patient_insurance_textField.setText(hasInsurance ? "Evet" : "Hayır");

        double totalCost = 0;

        // Tedavilerin toplam maliyetini hesapla
        for (Treatment treatment : treatments) {
            // Tedaviye ait ödeme var mı kontrol et
            if (treatment.getPayment() == null) {
                // Ödeme yapılmamışsa, bu tedavinin maliyetini toplama ekle
                Long treatment_id = treatment.getId();
                MedicalProcedureTreatment procedureTreatment = hospitalManagementService.getMedicalProcedureTreatmentRepository().findByTreatmentId(treatment_id);
                if (procedureTreatment != null) {
                    MedicalProcedure procedure = procedureTreatment.getMedicalProcedure();
                    if (procedure != null) {
                        totalCost += procedure.getCost();
                    }
                }
            }
        }

        // Toplam maliyeti total_price_textField'e yaz
        total_price_textField.setText(String.valueOf(totalCost));
    }



    protected void confirm_payment_function() {
		
	}
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TreasurerPage window = new TreasurerPage(null, null);
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
    public TreasurerPage(HospitalManagementService hospitalManagementService, InsuranceServer insuranceServer) {
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
        panel.setLayout(null); // Yerleştirme için mutlak konum kullanacağız

        // Başlık
        title = new JLabel("MUAYENE ÜCRETİ ÖDEME");
        title.setFont(new Font("Tahoma", Font.BOLD, 16));
        title.setBounds(161, 20, 229, 20);
        panel.add(title);

        // Sosyal güvenlik numarası alanı
        patient_social_no_label = new JLabel("Hasta Kimlik No:");
        patient_social_no_label.setBounds(37, 96, 150, 20);
        panel.add(patient_social_no_label);

        patient_social_no_textField = new JTextField();
        patient_social_no_textField.setBounds(210, 97, 200, 20);
        panel.add(patient_social_no_textField);

        // Sigorta numarası alanı
        patient_insurance_label = new JLabel("Sigorta Durumu:");
        patient_insurance_label.setBounds(37, 203, 150, 20);
        panel.add(patient_insurance_label);

        patient_insurance_textField = new JTextField();
        patient_insurance_textField.setBounds(210, 204, 200, 20);
        panel.add(patient_insurance_textField);

        // Toplam fiyat alanı
        total_price_label = new JLabel("Toplam Ücret:");
        total_price_label.setBounds(37, 252, 150, 20);
        panel.add(total_price_label);

        total_price_textField = new JTextField();
        total_price_textField.setBounds(210, 253, 200, 20);
        panel.add(total_price_textField);

        // Ödeme Türü alanı
        type_of_pay_label = new JLabel("Ödeme Türü:");
        type_of_pay_label.setBounds(37, 298, 150, 20);
        panel.add(type_of_pay_label);

        String[] paymentTypes = {"Nakit", "Kredi Kartı"};
        type_of_pay_combo = new JComboBox<>(paymentTypes);
        type_of_pay_combo.setBounds(210, 298, 200, 20);
        panel.add(type_of_pay_combo);

        // Hesapla butonu
        calculate_price_button = new JButton("Ücreti Hesapla");
        calculate_price_button.setBounds(428, 96, 80, 20);
        panel.add(calculate_price_button);
        calculate_price_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculate_price_function();
			}
		});

        // Onayla butonu
        confirm_payment_button = new JButton("Ödemeyi Onayla");
        confirm_payment_button.setBounds(178, 376, 150, 30);
        panel.add(confirm_payment_button);
        confirm_payment_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirm_payment_function();
			}
		});
    }



	

}
