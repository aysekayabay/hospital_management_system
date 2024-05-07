package ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.example.hospital_management_system.HospitalManagementService;
import com.example.hospital_management_system.entity.Appointment;
import com.example.hospital_management_system.entity.Clinic;
import com.example.hospital_management_system.entity.Doctor;
import com.example.hospital_management_system.entity.Patient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.sql.Timestamp;

import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InfoDeskPage {

	private JFrame frame;
	private JTable infoDeskPage_table;
	private JTextField infoDeskPage_PatientId_textField;
	private DefaultTableModel model;
	private HospitalManagementService hospitalManagementService;
	Patient patient;
	Timestamp timestampNow;
	JComboBox infoDeskPage_chooseClinic_comboBox = new JComboBox();
	private JTextField infoDeskPage_Date_textField;

	private void loadProceduresComboBox() {
		List<Clinic> clinics = hospitalManagementService.getClinicRepository().findAll();
		for (Clinic clinic : clinics) {
			System.out.println(clinic);
			infoDeskPage_chooseClinic_comboBox.addItem(clinic.getName());
		}
	}
	
	// Checking if patient has another appointment in the selected clinic
	public boolean isClinicAndPatientAccessible(Clinic clinic, Patient patient, Timestamp date) {
		List<Appointment> appointment = hospitalManagementService.getAppointmentRepository().findByAppointmentDateAfterAndPatientAndClinic(date, patient, clinic);
	    return appointment.isEmpty();
	}

	public static String turkishDayOfWeek(DayOfWeek dayOfWeek) {
		switch (dayOfWeek) {
		case MONDAY:
			return "Pazartesi";
		case TUESDAY:
			return "Salı";
		case WEDNESDAY:
			return "Çarşamba";
		case THURSDAY:
			return "Perşembe";
		case FRIDAY:
			return "Cuma";
		case SATURDAY:
			return "Cumartesi";
		case SUNDAY:
			return "Pazar";
		default:
			return "";
		}
	}

	public InfoDeskPage(HospitalManagementService hospitalManagementService) {
		this.hospitalManagementService = hospitalManagementService;
		loadProceduresComboBox();
		timestampNow = new Timestamp(System.currentTimeMillis());
		initialize();

	}

	public JFrame getFrame() {
		return frame;
	}

	private boolean isValidDateFormat(String date) {
		// XX-XX-XXXX formatında bir tarih metni için uygun regex
		String regex = "\\d{4}-\\d{2}-\\d{2}";
		return date.matches(regex);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 559, 539);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		Integer hourNow = timestampNow.toLocalDateTime().getHour();
		Integer minuteNow = timestampNow.toLocalDateTime().getMinute();
		Integer dayNow = timestampNow.toLocalDateTime().getDayOfMonth();
		Integer monthNow = timestampNow.toLocalDateTime().getMonthValue();
		Integer yearNow = timestampNow.toLocalDateTime().getYear();

		model = new DefaultTableModel();
		model.addColumn("Tarih");
		model.addColumn("Saat");
		model.addColumn("Doktor");

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 543, 500);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel infoDeskPage_header_jlabel = new JLabel("Hastane Randevu Kaydı");
		infoDeskPage_header_jlabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		infoDeskPage_header_jlabel.setBounds(189, 26, 200, 30);
		panel.add(infoDeskPage_header_jlabel);

		infoDeskPage_chooseClinic_comboBox.setToolTipText("");
		infoDeskPage_chooseClinic_comboBox.setBounds(10, 86, 92, 22);
		panel.add(infoDeskPage_chooseClinic_comboBox);

		JButton infoDeskPage_FindAppointment_Button = new JButton("Randevu Bul");
		infoDeskPage_FindAppointment_Button.setBounds(282, 86, 107, 23);
		panel.add(infoDeskPage_FindAppointment_Button);

		
		// Find the appointments with the given date and clinic
		infoDeskPage_FindAppointment_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				String clinicName = infoDeskPage_chooseClinic_comboBox.getSelectedItem().toString();
				String date = infoDeskPage_Date_textField.getText();

				if (date == null || clinicName == null) {
					JOptionPane.showMessageDialog(null, "Klinik seçin ya da uygun tarih girin!", "Hata",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (!isValidDateFormat(date)) {
					JOptionPane.showMessageDialog(null, "Uygun formatta tarih girin! XXXX-XX-XX", "Hata",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate nDate = LocalDate.parse(date, formatter);
				DayOfWeek dayOfWeek = nDate.getDayOfWeek();
				String turkishDay = turkishDayOfWeek(dayOfWeek);

				int dayEntered = nDate.getDayOfMonth();
				int monthEntered = nDate.getMonthValue();
				int yearEntered = nDate.getYear();

				if (yearEntered < yearNow || monthEntered < monthNow || dayEntered < dayNow) {
					JOptionPane.showMessageDialog(null, "Geçmiş bir tarih girmeyiniz!", "Hata",
							JOptionPane.ERROR_MESSAGE);

				}
				
				Clinic clinic = hospitalManagementService.getClinicRepository().findByname(clinicName);
				Doctor[] doctors = hospitalManagementService.getDoctorRepository().findByClinicId(clinic.getId());

				for (Doctor doctor : doctors) {
					System.out.println(doctor);
					Map<String, String> workingHours = doctor.getWorkingHours();
					for (Map.Entry<String, String> entry : workingHours.entrySet()) {
						// '{"Pazartesi": "09:00-17:00", "Salı": "09:00-17:00"}',
						String day = entry.getKey();
						if (!day.equals(turkishDay)) {
							continue;
						}
						String timeRange = entry.getValue();

						Integer startHour = Integer.parseInt(timeRange.substring(0, 2));
						Integer startMinute = Integer.parseInt(timeRange.substring(3, 5));

						Integer endHour = Integer.parseInt(timeRange.substring(6, 8));
						Integer endMinute = Integer.parseInt(timeRange.substring(9, 11));

						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						Timestamp timestamp = null;
						try {
							Date parsedDate = dateFormat.parse(date);
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(parsedDate);
							timestamp = new Timestamp(calendar.getTimeInMillis());
							// System.out.println(timestamp);
						} catch (ParseException e1) {
							e1.printStackTrace();
						}

						List<Appointment> appointments = hospitalManagementService.getAppointmentRepository()
								.findByDoctorAndAppointmentDate(doctor, timestamp);
						if (appointments.isEmpty()) {
							System.out.println("Boş bu");
						}
						Map<Integer, Boolean> filledHours = new HashMap<>();
						for (Appointment app : appointments) {
							System.out.println(app);
							Calendar calendar = Calendar.getInstance();
							calendar.setTimeInMillis(app.getAppointmentDate().getTime());
							Integer hour = calendar.get(Calendar.HOUR_OF_DAY);
							Integer minute = calendar.get(Calendar.MINUTE);

							// System.out.print("Hour+minute:");
							// System.out.println(hour + minute);
							filledHours.put(hour + minute, true);
						}
						// Appointment{id=1, appointmentDate=2024-05-06 10:00:00.0, doctor='1',
						// clinic=1, patient=12345678901}

						if (yearEntered == yearNow && monthEntered == monthNow && dayEntered == dayNow) {
							if (!(startHour > hourNow || startMinute > minuteNow)) {
								if (minuteNow > 30) {
									startHour = hourNow + 1;
									startMinute = 0;
								} else {
									startHour = hourNow;
									startMinute = 30;
								}
							}
						}
						while ((float)startHour +  (float)startMinute/60 <= (float)endHour + (float)endMinute/60){
							if (startHour == 12) {
								// Lunch break
							} else {
								Integer sumTime = startHour + startMinute;
								if (!filledHours.containsKey(sumTime)) {
									model.addRow(new Object[] { date,
											startHour.toString() + ":" + (startMinute == 0 ? "00" : "30"),
											doctor.getFirstName() + " " + doctor.getLastName() });
								}
							}
							if (startMinute == 30) {
								startHour++;
								startMinute = 0;
							} else {
								startMinute = 30;
							}
						}
					}
				}
			}
		});
		

		JButton infoDeskPage_AlternativeAppointment_Button = new JButton("Alternatif Tarih");
		// Display alternative appointments with given clinic name (next 15 appointment)
		infoDeskPage_AlternativeAppointment_Button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        model.setRowCount(0);
		        String clinicName = infoDeskPage_chooseClinic_comboBox.getSelectedItem().toString();
		        Clinic clinic = hospitalManagementService.getClinicRepository().findByname(clinicName);
		        Doctor[] doctors = hospitalManagementService.getDoctorRepository().findByClinicId(clinic.getId());
		        
		        String dateNow = String.format("%04d-%02d-%02d", yearNow, monthNow, dayNow);
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		        LocalDate nDate = LocalDate.parse(dateNow, formatter);
		        DayOfWeek dayOfWeek = nDate.getDayOfWeek();
		        String turkishDay = turkishDayOfWeek(dayOfWeek);
		        
		        for (Doctor doctor : doctors) {
		            Map<String, String> workingHours = doctor.getWorkingHours();
		            int i = 0;
		            while (i < 15) {
		                if (!workingHours.containsKey(turkishDay)) {
		                    nDate = nDate.plusDays(1);
		                    dayOfWeek = nDate.getDayOfWeek();
		                    turkishDay = turkishDayOfWeek(dayOfWeek);
		                } else {
		                    String timeRange = workingHours.get(turkishDay);

		                    Integer startHour = Integer.parseInt(timeRange.substring(0, 2));
		                    Integer startMinute = Integer.parseInt(timeRange.substring(3, 5));

		                    Integer endHour = Integer.parseInt(timeRange.substring(6, 8));
		                    Integer endMinute = Integer.parseInt(timeRange.substring(9, 11));

		                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		                    Timestamp timestamp = null;
		                    try {
		                        Date parsedDate = dateFormat.parse(dateNow);
		                        Calendar calendar = Calendar.getInstance();
		                        calendar.setTime(parsedDate);
		                        timestamp = new Timestamp(calendar.getTimeInMillis());
		                    } catch (ParseException e1) {
		                        e1.printStackTrace();
		                    }

		                    List<Appointment> appointments = hospitalManagementService.getAppointmentRepository()
		                            .findByDoctorAndAppointmentDate(doctor, timestamp);
		                    if (appointments.isEmpty()) {
		                        System.out.println("Boş bu");
		                    }
		                    Map<Integer, Boolean> filledHours = new HashMap<>();
		                    for (Appointment app : appointments) {
		                        Calendar calendar = Calendar.getInstance();
		                        calendar.setTimeInMillis(app.getAppointmentDate().getTime());
		                        Integer hour = calendar.get(Calendar.HOUR_OF_DAY);
		                        Integer minute = calendar.get(Calendar.MINUTE);
		                        filledHours.put(hour + minute, true);
		                    }

		                    while (i < 15 && (float) startHour + (float) startMinute / 60 <= (float) endHour + (float) endMinute / 60) {
		                        if (startHour == 12) {
		                            // Lunch break
		                        } else {
		                            Integer sumTime = startHour + startMinute;
		                            if (!filledHours.containsKey(sumTime)) {
		                                model.addRow(new Object[]{nDate,
		                                        startHour.toString() + ":" + (startMinute == 0 ? "00" : "30"),
		                                        doctor.getFirstName() + " " + doctor.getLastName()});
		                                i++;
		                            }
		                        }
		                        if (startMinute == 30) {
		                            startHour++;
		                            startMinute = 0;
		                        } else {
		                            startMinute = 30;
		                        }
		                    }
		                    nDate = nDate.plusDays(1);
		                    dayOfWeek = nDate.getDayOfWeek();
		                    turkishDay = turkishDayOfWeek(dayOfWeek);
		                }
		            }
		        }
		    }
		});

		infoDeskPage_AlternativeAppointment_Button.setBounds(413, 86, 120, 23);
		panel.add(infoDeskPage_AlternativeAppointment_Button);

		JScrollPane infoDeskPage_scrollPane = new JScrollPane();
		infoDeskPage_scrollPane.setBackground(new Color(192, 192, 192));
		infoDeskPage_scrollPane.setBounds(10, 114, 523, 297);
		panel.add(infoDeskPage_scrollPane);

		// Tabloyu oluştur ve modele bağla
		infoDeskPage_table = new JTable(model);
		infoDeskPage_scrollPane.setViewportView(infoDeskPage_table);

		JTextField infoDeskPage_PatientId_textField = new JTextField();
		infoDeskPage_PatientId_textField.setBackground(new Color(192, 192, 192));
		infoDeskPage_PatientId_textField.setBounds(167, 433, 138, 30);
		infoDeskPage_PatientId_textField.setColumns(10);
		infoDeskPage_PatientId_textField.setForeground(Color.GRAY);
		infoDeskPage_PatientId_textField.setText("Hasta Kimlik");
		panel.add(infoDeskPage_PatientId_textField);

		infoDeskPage_PatientId_textField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (infoDeskPage_PatientId_textField.getText().equals("Hasta Kimlik")) {
					infoDeskPage_PatientId_textField.setText("");
					infoDeskPage_PatientId_textField.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (infoDeskPage_PatientId_textField.getText().isBlank()) {
					infoDeskPage_PatientId_textField.setForeground(Color.GRAY);
					infoDeskPage_PatientId_textField.setText("Hasta Kimlik");
				}
			}
		});

		
		// Confirming appointment
		JButton infoDeskPage_AppointmentConfirm_Button = new JButton("Randevuyu Onayla");
		infoDeskPage_AppointmentConfirm_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = infoDeskPage_table.getSelectedRow();
				if (selectedRow != -1) { 
					String date = infoDeskPage_table.getValueAt(selectedRow, 0).toString();
					String hour = infoDeskPage_table.getValueAt(selectedRow, 1).toString();
					String dateTimeString = date + " " + hour;

				
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					Date dateTime = null;
					try {
						dateTime = dateFormat.parse(dateTimeString);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					Timestamp timestamp = new Timestamp(dateTime.getTime());

					String doktorName = infoDeskPage_table.getValueAt(selectedRow, 2).toString();
					String[] nameParts = doktorName.split(" ");
					String firstName = nameParts[0];
					String lastName = nameParts[1];
					Doctor doctor = hospitalManagementService.getDoctorRepository()
							.findByFirstNameAndLastName(firstName, lastName);

					Appointment appointment = new Appointment();
					appointment.setPatient(patient);
					appointment.setDoctorID(doctor);
					appointment.setClinic(doctor.getClinic());
					appointment.setAppointmentDate(timestamp);
					
					if(!isClinicAndPatientAccessible(doctor.getClinic(),patient,timestampNow)){
						JOptionPane.showMessageDialog(null, "Hastanın halihazırda randevusu var!", "Hata", JOptionPane.ERROR_MESSAGE);
					}else {
						hospitalManagementService.getAppointmentRepository().save(appointment);
						JOptionPane.showMessageDialog(null, "Randevu Kayıt Başarılı!", "Hata", JOptionPane.ERROR_MESSAGE);
						System.out.println(
								"Onaylanan Randevu Bilgileri: Tarih=" + date + ", Saat=" + hour + ", Doktor=" + doctor);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Lütfen bir randevu seçin!", "Hata", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		infoDeskPage_AppointmentConfirm_Button.setBackground(new Color(192, 192, 192));
		infoDeskPage_AppointmentConfirm_Button.setBounds(357, 433, 127, 28);
		panel.add(infoDeskPage_AppointmentConfirm_Button);
		infoDeskPage_AppointmentConfirm_Button.setVisible(false);

		JButton infoDeskPage_ConfirmPatient_Button = new JButton("Hastayı Doğrula");
		infoDeskPage_ConfirmPatient_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String patientID = infoDeskPage_PatientId_textField.getText();
				patient = hospitalManagementService.getPatientRepository().findBySocialNumber(patientID);
				if (patient == null) {
					JOptionPane.showMessageDialog(null, "Kayıtlı hasta bulunamadı!", "Hata", JOptionPane.ERROR_MESSAGE);
				} else {
					infoDeskPage_ConfirmPatient_Button.setVisible(false);
					infoDeskPage_AppointmentConfirm_Button.setVisible(true);
					infoDeskPage_PatientId_textField.setVisible(false);
				}
			}
		});
		infoDeskPage_ConfirmPatient_Button.setBackground(new Color(192, 192, 192));
		infoDeskPage_ConfirmPatient_Button.setBounds(357, 433, 127, 32);
		panel.add(infoDeskPage_ConfirmPatient_Button);

		infoDeskPage_Date_textField = new JTextField();
		infoDeskPage_Date_textField.setBackground(new Color(192, 192, 192));
		infoDeskPage_Date_textField.setBounds(140, 87, 107, 21);
		panel.add(infoDeskPage_Date_textField);
		infoDeskPage_Date_textField.setColumns(10);

		infoDeskPage_Date_textField.setForeground(Color.GRAY);
		infoDeskPage_Date_textField.setText("YIL-AY-GÜN");

		infoDeskPage_Date_textField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (infoDeskPage_Date_textField.getText().equals("YIL-AY-GÜN")) {
					infoDeskPage_Date_textField.setText("");
					infoDeskPage_Date_textField.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (infoDeskPage_Date_textField.getText().isEmpty()) {
					infoDeskPage_Date_textField.setForeground(Color.GRAY);
					infoDeskPage_Date_textField.setText("YIL-AY-GÜN");
				}
			}
		});

		JLabel infoDeskPage_Date_Label = new JLabel("Tarih");
		infoDeskPage_Date_Label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		infoDeskPage_Date_Label.setBounds(140, 67, 46, 14);
		panel.add(infoDeskPage_Date_Label);

	}
}
