package ui;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;


import com.example.hospital_management_system.HospitalManagementService;
import com.example.hospital_management_system.entity.Doctor;
import com.example.hospital_management_system.entity.Staff;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginPage {

	private JFrame frame;
	private JTextField login_id_txtArea;
	private JTextField login_password_textArea;
	private HospitalManagementService hospitalManagementService;



	/**
	 * Launch the application.
	 */
	
	public JFrame getFrame() {
		return frame;
	}
	
	/**
	 * Create the application.
	 */
	
	public LoginPage(HospitalManagementService hospitalManagementService) {
		this.hospitalManagementService = hospitalManagementService;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 453, 451);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 444, 418);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel login_header_label = new JLabel("Hastane Giriş");
		login_header_label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		login_header_label.setBounds(160, 27, 131, 50);
		panel.add(login_header_label);
		
		login_id_txtArea = new JTextField();
		login_id_txtArea.setBackground(new Color(192, 192, 192));
		login_id_txtArea.setBounds(146, 111, 145, 20);
		panel.add(login_id_txtArea);
		login_id_txtArea.setColumns(10);
		
		JLabel login_id_label = new JLabel("ID");
		login_id_label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		login_id_label.setBounds(77, 112, 46, 14);
		panel.add(login_id_label);
		
		login_password_textArea = new JTextField();
		login_password_textArea.setColumns(10);
		login_password_textArea.setBackground(Color.LIGHT_GRAY);
		login_password_textArea.setBounds(146, 180, 145, 20);
		panel.add(login_password_textArea);
		
		JLabel login_password_label = new JLabel("Şifre");
		login_password_label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		login_password_label.setBounds(77, 181, 46, 14);
		panel.add(login_password_label);
		
		JButton login_LogIn_buttn = new JButton("Giriş Yap");
		login_LogIn_buttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = login_id_txtArea.getText();
		        String password = login_password_textArea.getText();
		        
		        if (username.isEmpty() || password.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Kullanıcı adı veya şifre alanları boş olamaz!", "Hata", JOptionPane.ERROR_MESSAGE);
		        } 
		        
		        if(username.length() < 3) {
		        	 JOptionPane.showMessageDialog(null, "Dr veya St ile başlayan idnizi girin! 3'ten az", "Hata", JOptionPane.ERROR_MESSAGE);
		        }else { 
		        	String user = username.substring(0,2);
		        	System.out.println(user);
		        	System.out.println(password);

		        	if(user.equals("Dr")) {
		        		Long id = Long.parseLong(username.substring(2,username.length()));
		        		Doctor doctor = hospitalManagementService.getDoctorRepository().findById(id).orElse(null);
		        		if(doctor.getPassword().equals(password)) {
		        			JOptionPane.showMessageDialog(null, "Doktor girişi başarılı!", "Başarılı", JOptionPane.ERROR_MESSAGE);
		        			DoctorPage window = new DoctorPage(doctor , hospitalManagementService);
		        			window.getFrame().setVisible(true);
		        			frame.dispose();
		        		}else {
		        			JOptionPane.showMessageDialog(null, "Id veya şifre hatalı!", "Hata", JOptionPane.ERROR_MESSAGE);
		        		}
		        	}else if(user.equals("St")) {
		        		Long id = Long.parseLong(username.substring(2,username.length()));
		        		Staff staff = hospitalManagementService.getStaffRepository().findById(id).orElse(null);
		        		if(staff.getPassword().equals(password)) {
		        			JOptionPane.showMessageDialog(null, "Staff girişi başarılı!", "Başarılı", JOptionPane.ERROR_MESSAGE);
		        			String staff_type = staff.getStaffType().toString();
			        		if (staff_type.equals("treasurer")) {
			        			TreasurerPage window = new TreasurerPage(hospitalManagementService);
			        			window.getFrame().setVisible(true);
			        			frame.dispose();
			        		} else if (staff_type.equals("registar")) {
			        			
			        		} else {
			        			
			        		}
		        		}else {
		        			JOptionPane.showMessageDialog(null, "Id veya şifre hatalı!", "Hata", JOptionPane.ERROR_MESSAGE);
		        		}
		        	}else {
		        		 JOptionPane.showMessageDialog(null, "Dr veya St ile başlayan idnizi girin!", "Hata", JOptionPane.ERROR_MESSAGE);
		        	}
		        }
			}
		});

		login_LogIn_buttn.setBackground(new Color(192, 192, 192));
		login_LogIn_buttn.setBounds(146, 283, 145, 23);
		panel.add(login_LogIn_buttn);
		
		
	}
}
