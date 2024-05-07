package ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.List;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

import com.example.hospital_management_system.HospitalManagementService;
import com.example.hospital_management_system.entity.Appointment;
import com.example.hospital_management_system.entity.Doctor;
import com.example.hospital_management_system.entity.Patient;

@SuppressWarnings("serial")
//Define a custom table model that does not allow cell editing
class NonEditableModel extends DefaultTableModel {

 NonEditableModel(Object[] columnNames, int rowCount) {
     super(columnNames, rowCount);
 }

 @Override
 public boolean isCellEditable(int row, int column) {
     return false;  // This will make all cells in the table non-editable
 }
}




@Component
public class DoctorPage {

    private HospitalManagementService hospitalManagementService;
    private JFrame frame;
    private JTable table;
    private Doctor doctor;
    private List<Appointment> todaysAppointments;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DoctorPage window = new DoctorPage(null, null);
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
    @Autowired
    public DoctorPage(Doctor doctor, HospitalManagementService hospitalManagementService) {
        if (hospitalManagementService == null || doctor == null) {
            throw new IllegalArgumentException("HospitalManagementService and Doctor must not be null");
        }
        this.hospitalManagementService = hospitalManagementService;
        this.doctor = doctor;
        try {
            todaysAppointments = fetchTodaysAppointments();
            initialize();
            displayAppointments();
            this.frame.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error initializing Doctor Page: " + e.getMessage(), "Initialization Error", JOptionPane.ERROR_MESSAGE);
            // Consider closing the frame or offering retry options
        }
    }

    private void initialize() {
        frame = new JFrame("Doktor Ekranı - " + getPageTitle(doctor));
        frame.setBounds(100, 100, 532, 612);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel doctor_title_label = new JLabel(getPageTitle(doctor), SwingConstants.CENTER);
        doctor_title_label.setFont(new Font("Tahoma", Font.BOLD, 16));
        frame.add(doctor_title_label, BorderLayout.NORTH);

        JPanel doctor_page_panel = new JPanel();
        doctor_page_panel.setLayout(new BorderLayout());
        frame.add(doctor_page_panel, BorderLayout.CENTER);
        doctor_page_panel.setBackground(Color.WHITE);

        JLabel lblHeading = new JLabel("Bugünün Randevuları", SwingConstants.CENTER);
        lblHeading.setFont(new Font("Tahoma", Font.PLAIN, 18));
        doctor_page_panel.add(lblHeading, BorderLayout.NORTH);

        String[] columnNames = {"Saat", "Hasta Kimlik No", "Hasta Adı Soyadı"};
        table = new JTable(new NonEditableModel(columnNames, 0));  // Use the custom model here
        JScrollPane scrollPane = new JScrollPane(table);
        doctor_page_panel.add(scrollPane, BorderLayout.CENTER);

        JButton doctor_view_patient_button = new JButton("Hasta Bilgilerini Görüntüle");
        doctor_view_patient_button.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) { // Ensure a row is selected
                // Retrieve the appointment based on the selected row
                Appointment selectedAppointment = todaysAppointments.get(selectedRow);

                // Now pass the selected appointment to the method that handles the next view
                directToViewPatientAndAddRecordPage(selectedAppointment);
            } else {
                JOptionPane.showMessageDialog(frame, "Lütfen bir randevu seçin.", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });
        doctor_page_panel.add(doctor_view_patient_button, BorderLayout.SOUTH);
    }

    private List<Appointment> fetchTodaysAppointments() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        // Set to yesterday for testing purposes
        // calendar.add(Calendar.DATE, -1);
        Date startDate = calendar.getTime();  // Start of today

        // Set to the end of yesterday for testing purposes
        calendar.add(Calendar.DATE, 1);
        calendar.add(Calendar.MILLISECOND, -1);
        Date endDate = calendar.getTime();    // End of today

        return hospitalManagementService.getAppointmentRepository().findByDoctorAndAppointmentDateBetween(doctor, startDate, endDate);
    }
    
    private void displayAppointments() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing data
        
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm"); // Format to display hour and minute
        
        for (Appointment appointment : todaysAppointments) {
            String formattedTime = timeFormat.format(appointment.getAppointmentDate()); // Format the date
            
            model.addRow(new Object[]{
                formattedTime, // Only hour and minute of the appointment
                appointment.getPatient().getSocialNumber(),  // Assuming this is the patient ID
                appointment.getPatient().getFirstName() + " " + appointment.getPatient().getLastName() // Patient full name
            });
        }
    }

    private String getPageTitle(Doctor doctor) {
        return doctor.getQualification() + " " + doctor.getSpecialty() + " - " + doctor.getFirstName() + " " + doctor.getLastName();
    }
    
    /**
     * A placeholder method to simulate handling patient details based on the selected row.
     * @param patientId The ID of the selected patient.
     * @param patientName The name of the selected patient.
     */
    private void directToViewPatientAndAddRecordPage(Appointment appointment) {
        // System.out.println("Selected Appointment: " + appointment);
        Patient patient = appointment.getPatient();
        
        //System.out.println("Patient info: "+ patient);
        ViewPatientAndAddRecordPage window = new ViewPatientAndAddRecordPage(patient, doctor, appointment, hospitalManagementService);
        window.getFrame().setVisible(true);
    }

	public JFrame getFrame() {
		return frame;
	}

	public Object getTable() {
		// TODO Auto-generated method stub
		return table;
	}
}