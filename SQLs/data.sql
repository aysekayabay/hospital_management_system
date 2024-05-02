INSERT INTO InsuranceServer (name, peopleWithInsurance)
VALUES ('InsuranceCo1', ARRAY['John Doe', 'Jane Smith', 'Alice Johnson']),
       ('InsuranceCo2', ARRAY['Michael Brown', 'Emily Davis', 'David Wilson']);

INSERT INTO Policlinic (name, location, collabratedInsuranceServerID)
VALUES ('Policlinic A', 'Location A', 1),
       ('Policlinic B', 'Location B', 2),
       ('Policlinic C', 'Location C', 1),
       ('Policlinic D', 'Location D', 2);

INSERT INTO Clinic (name, policlinicID)
VALUES ('Clinic A', 1),
       ('Clinic B', 1),
       ('Clinic C', 2),
       ('Clinic D', 2),
       ('Clinic E', 3),
       ('Clinic F', 3),
       ('Clinic G', 4),
       ('Clinic H', 4),
       ('Clinic I', 4),
       ('Clinic J', 3);


SELECT add_staff('Melih', 'Tuna', 'FSM Erkek Yurdu', 1);
SELECT add_staff('John', 'Doe', '123 Main Street', 2);
SELECT add_staff('Alice', 'Smith', '456 Elm Street', 3);
SELECT add_staff('Michael', 'Johnson', '789 Oak Street', 1);
SELECT add_staff('Emily', 'Davis', '101 Pine Street', 4);
SELECT add_staff('David', 'Wilson', '222 Maple Street', 2);

-- Doctor eklemek için
SELECT add_doctor(
    'John',
    'Doe',
    '123 Main Street',
    'Pediatrics',
    'MD',
    '{"Monday": [8, 16], "Tuesday": [9, 17], "Wednesday": [8, 15], "Thursday": [10, 18], "Friday": [8, 17]}',
    1
);
SELECT add_doctor(
    'Alice',
    'Smith',
    '456 Elm Street',
    'Cardiology',
    'MD',
    '{"Monday": [9, 17], "Tuesday": [10, 18], "Wednesday": [9, 16], "Thursday": [11, 19], "Friday": [9, 18]}',
    2
);
SELECT add_doctor(
    'Michael',
    'Johnson',
    '789 Oak Street',
    'Orthopedics',
    'MD',
    '{"Monday": [8, 15], "Tuesday": [9, 16], "Wednesday": [8, 14], "Thursday": [10, 17], "Friday": [8, 16]}',
    3
);
SELECT add_doctor(
    'Emily',
    'Davis',
    '101 Pine Street',
    'Neurology',
    'MD',
    '{"Monday": [10, 18], "Tuesday": [11, 19], "Wednesday": [10, 17], "Thursday": [12, 20], "Friday": [10, 19]}',
    4
);
SELECT add_doctor(
    'David',
    'Wilson',
    '222 Maple Street',
    'Dermatology',
    'MD',
    '{"Monday": [8, 16], "Tuesday": [9, 17], "Wednesday": [8, 15], "Thursday": [10, 18], "Friday": [8, 17]}',
    5
);

SELECT add_doctor(
    'Laura',
    'Williams',
    '333 Oak Avenue',
    'Gynecology',
    'MD',
    '{"Monday": [9, 17], "Tuesday": [10, 18], "Wednesday": [9, 16], "Thursday": [11, 19], "Friday": [9, 18]}',
    6
);
SELECT add_doctor(
    'Daniel',
    'Martinez',
    '444 Elm Avenue',
    'Oncology',
    'MD',
    '{"Monday": [8, 16], "Tuesday": [9, 17], "Wednesday": [8, 15], "Thursday": [10, 18], "Friday": [8, 17]}',
    7
);
SELECT add_doctor(
    'Olivia',
    'Anderson',
    '555 Maple Avenue',
    'Psychiatry',
    'MD',
    '{"Monday": [10, 18], "Tuesday": [11, 19], "Wednesday": [10, 17], "Thursday": [12, 20], "Friday": [10, 19]}',
    8
);
SELECT add_doctor(
    'Ethan',
    'Garcia',
    '666 Pine Avenue',
    'Urology',
    'MD',
    '{"Monday": [8, 15], "Tuesday": [9, 16], "Wednesday": [8, 14], "Thursday": [10, 17], "Friday": [8, 16]}',
    9
);
SELECT add_doctor(
    'Mia',
    'Lopez',
    '777 Cedar Avenue',
    'Endocrinology',
    'MD',
    '{"Monday": [9, 17], "Tuesday": [10, 18], "Wednesday": [9, 16], "Thursday": [11, 19], "Friday": [9, 18]}',
    10
);

INSERT INTO Patient (socialNumber, firstName, lastName, gender, address, birthDate, email, phone)
VALUES ('12345678901', 'Emma', 'Johnson', 'Female', '123 Main Street', '1990-05-15', 'emma@example.com', '5551234567'),
       ('23456789012', 'Noah', 'Williams', 'Male', '456 Elm Street', '1985-08-21', 'noah@example.com', '5552345678'),
       ('34567890123', 'Olivia', 'Brown', 'Female', '789 Oak Street', '1978-03-10', 'olivia@example.com', '5553456789'),
       ('45678901234', 'Liam', 'Jones', 'Male', '101 Pine Street', '1993-11-27', 'liam@example.com', '5554567890'),
       ('56789012345', 'Ava', 'Garcia', 'Female', '222 Maple Street', '1982-07-08', 'ava@example.com', '5555678901'),
       ('67890123456', 'William', 'Martinez', 'Male', '333 Cedar Street', '1975-01-19', 'william@example.com', '5556789012'),
       ('78901234567', 'Isabella', 'Lopez', 'Female', '444 Oak Avenue', '1989-09-03', 'isabella@example.com', '5557890123'),
       ('89012345678', 'James', 'Hernandez', 'Male', '555 Elm Avenue', '1970-12-14', 'james@example.com', '5558901234'),
       ('90123456789', 'Sophia', 'Rodriguez', 'Female', '666 Maple Avenue', '1987-06-25', 'sophia@example.com', '5559012345'),
       ('01234567890', 'Logan', 'Gonzalez', 'Male', '777 Pine Avenue', '1984-04-30', 'logan@example.com', '5550123456'),
       ('12345678910', 'Amelia', 'Perez', 'Female', '888 Cedar Avenue', '1995-02-17', 'amelia@example.com', '5551234567'),
       ('23456789021', 'Benjamin', 'Torres', 'Male', '999 Oak Street', '1980-10-05', 'benjamin@example.com', '5552345678'),
       ('34567890132', 'Mia', 'Flores', 'Female', '111 Elm Street', '1973-08-11', 'mia@example.com', '5553456789'),
       ('45678901243', 'Elijah', 'Rivera', 'Male', '222 Maple Street', '1986-12-29', 'elijah@example.com', '5554567890'),
       ('56789012354', 'Charlotte', 'Gomez', 'Female', '333 Cedar Street', '1992-07-23', 'charlotte@example.com', '5555678901'),
       ('67890123465', 'Henry', 'Mitchell', 'Male', '444 Oak Avenue', '1977-03-08', 'henry@example.com', '5556789012'),
       ('78901234576', 'Grace', 'Lee', 'Female', '555 Elm Avenue', '1998-05-20', 'grace@example.com', '5557890123'),
       ('89012345687', 'Jackson', 'Scott', 'Male', '666 Maple Avenue', '1979-11-18', 'jackson@example.com', '5558901234'),
       ('90123456798', 'Victoria', 'Green', 'Female', '777 Pine Avenue', '1991-09-09', 'victoria@example.com', '5559012345'),
       ('01234567809', 'Gabriel', 'Adams', 'Male', '888 Cedar Avenue', '1976-04-05', 'gabriel@example.com', '5550123456');

INSERT INTO Payment (amount, paymentDate, paymentMethod, patientSocialNumber)
VALUES (100.00, NOW(), 'Credit Card', '12345678901'),
       (150.00, NOW(), 'Cash', '23456789012'),
       (200.00, NOW(), 'Debit Card', '34567890123'),
       (250.00, NOW(), 'Credit Card', '45678901234'),
       (300.00, NOW(), 'Cash', '56789012345'),
       (350.00, NOW(), 'Debit Card', '67890123456'),
       (400.00, NOW(), 'Credit Card', '78901234567'),
       (450.00, NOW(), 'Cash', '89012345678'),
       (500.00, NOW(), 'Debit Card', '90123456789'),
       (550.00, NOW(), 'Credit Card', '01234567890'),
       (600.00, NOW(), 'Cash', '12345678910'),
       (650.00, NOW(), 'Debit Card', '23456789021'),
       (700.00, NOW(), 'Credit Card', '34567890132'),
       (750.00, NOW(), 'Cash', '45678901243'),
       (800.00, NOW(), 'Debit Card', '56789012354'),
       (850.00, NOW(), 'Credit Card', '67890123465'),
       (900.00, NOW(), 'Cash', '78901234576'),
       (950.00, NOW(), 'Debit Card', '89012345687'),
       (1000.00, NOW(), 'Credit Card', '90123456798'),
       (1050.00, NOW(), 'Cash', '01234567809'),
       (1100.00, NOW(), 'Debit Card', '12345678901'),
       (1150.00, NOW(), 'Credit Card', '23456789012'),
       (1200.00, NOW(), 'Cash', '34567890123'),
       (1250.00, NOW(), 'Debit Card', '45678901234');

INSERT INTO Appointment (appointmentDate, doctorID, clinicID, patientsocialNumber)
VALUES (NOW() + INTERVAL '1 day', 'dc_1', 1, '12345678901'),
       (NOW() + INTERVAL '2 day', 'dc_2', 2, '23456789012'),
       (NOW() + INTERVAL '3 day', 'dc_3', 3, '34567890123'),
       (NOW() + INTERVAL '4 day', 'dc_4', 4, '45678901234'),
       (NOW() + INTERVAL '5 day', 'dc_5', 5, '56789012345'),
       (NOW() + INTERVAL '6 day', 'dc_6', 1, '67890123456'),
       (NOW() + INTERVAL '7 day', 'dc_7', 2, '78901234567'),
       (NOW() + INTERVAL '8 day', 'dc_8', 3, '89012345678'),
       (NOW() + INTERVAL '9 day', 'dc_9', 4, '90123456789'),
       (NOW() + INTERVAL '10 day', 'dc_10', 5, '01234567890'),
       (NOW() + INTERVAL '11 day', 'dc_1', 1, '12345678910'),
       (NOW() + INTERVAL '12 day', 'dc_2', 2, '23456789021'),
       (NOW() + INTERVAL '13 day', 'dc_3', 3, '34567890132'),
       (NOW() + INTERVAL '14 day', 'dc_4', 4, '45678901243'),
       (NOW() + INTERVAL '15 day', 'dc_5', 5, '56789012354'),
       (NOW() + INTERVAL '16 day', 'dc_6', 1, '67890123465'),
       (NOW() + INTERVAL '17 day', 'dc_7', 2, '78901234576'),
       (NOW() + INTERVAL '18 day', 'dc_8', 3, '89012345687'),
       (NOW() + INTERVAL '19 day', 'dc_9', 4, '90123456798'),
       (NOW() + INTERVAL '20 day', 'dc_10', 5, '01234567809'),
       (NOW() + INTERVAL '21 day', 'dc_1', 1, '12345678901'),
       (NOW() + INTERVAL '22 day', 'dc_2', 2, '23456789012'),
       (NOW() + INTERVAL '23 day', 'dc_3', 3, '34567890123'),
       (NOW() + INTERVAL '24 day', 'dc_4', 4, '45678901234'),
       (NOW() + INTERVAL '25 day', 'dc_5', 5, '56789012345');

INSERT INTO Treatment (diagnosis, prescription, report, refferalNote, treatmentDate, clinicID, doctorID, paymentID, appointmentID, patientsocialNumber, treatmentDuration)
VALUES ('Cold', 'Paracetamol', 'Patient recovered well', 'Follow up in a week', NOW() - INTERVAL '1 day', 1, 'dc_1', 1, 1, '12345678901', '00:30:00'),
       ('Fever', 'Antibiotics', 'Patient responded well to treatment', 'No further treatment needed', NOW() - INTERVAL '2 day', 2, 'dc_2', 2, 2, '23456789012', '01:00:00'),
       ('Headache', 'Rest and hydration', 'Patient feels much better now', 'No additional recommendations', NOW() - INTERVAL '3 day', 3, 'dc_3', 3, 3, '34567890123', '00:45:00'),
       ('Sprained ankle', 'Pain relievers and rest', 'Ankle swelling reduced', 'Follow up in two weeks', NOW() - INTERVAL '4 day', 4, 'dc_4', 4, 4, '45678901234', '01:15:00'),
       ('Sore throat', 'Throat lozenges', 'Throat inflammation subsided', 'No further treatment needed', NOW() - INTERVAL '5 day', 5, 'dc_5', 5, 5, '56789012345', '00:40:00'),
       ('Allergy', 'Antihistamines', 'Allergy symptoms alleviated', 'No additional recommendations', NOW() - INTERVAL '6 day', 1, 'dc_6', 6, 6, '67890123456', '00:55:00'),
       ('Acne', 'Topical cream', 'Skin condition improved', 'Follow up in one month', NOW() - INTERVAL '7 day', 2, 'dc_7', 7, 7, '78901234567', '00:25:00'),
       ('Stomach ache', 'Antacids', 'Patient feels relief', 'No further treatment needed', NOW() - INTERVAL '8 day', 3, 'dc_8', 8, 8, '89012345678', '01:10:00'),
       ('Influenza', 'Rest and fluids', 'Patient recovering well', 'Follow up in two days', NOW() - INTERVAL '9 day', 4, 'dc_9', 9, 9, '90123456789', '00:35:00'),
       ('Eye infection', 'Antibiotic eye drops', 'Infection cleared up', 'No additional recommendations', NOW() - INTERVAL '10 day', 5, 'dc_10', 10, 10, '01234567890', '01:20:00'),
       -- Add more treatments here as needed
       ('Back pain', 'Muscle relaxants', 'Pain reduced significantly', 'No further treatment needed', NOW() - INTERVAL '11 day', 1, 'dc_1', 11, 11, '12345678910', '00:50:00'),
       ('Migraine', 'Pain medication', 'Migraine symptoms improved', 'Follow up in one week', NOW() - INTERVAL '12 day', 2, 'dc_2', 12, 12, '23456789021', '01:05:00'),
       ('Broken arm', 'Cast', 'Arm healing properly', 'Follow up in three weeks', NOW() - INTERVAL '13 day', 3, 'dc_3', 13, 13, '34567890132', '00:30:00'),
       ('Anxiety', 'Therapy sessions', 'Patient coping better', 'No additional recommendations', NOW() - INTERVAL '14 day', 4, 'dc_4', 14, 14, '45678901243', '01:15:00'),
       ('High blood pressure', 'Medication and lifestyle changes', 'Blood pressure under control', 'Regular monitoring advised', NOW() - INTERVAL '15 day', 5, 'dc_5', 15, 15, '56789012354', '00:45:00'),
       ('Skin rash', 'Topical ointment', 'Rash clearing up', 'No further treatment needed', NOW() - INTERVAL '16 day', 1, 'dc_6', 16, 16, '67890123465', '00:55:00'),
       ('Diabetes', 'Insulin therapy', 'Blood sugar levels stabilized', 'Regular check-ups recommended', NOW() - INTERVAL '17 day', 2, 'dc_7', 17, 17, '78901234576', '01:30:00'),
       ('Sprained wrist', 'Splint', 'Wrist mobility improving', 'Follow up in two weeks', NOW() - INTERVAL '18 day', 3, 'dc_8', 18, 18, '89012345687', '00:40:00'),
       ('Conjunctivitis', 'Eye drops', 'Eye irritation reduced', 'No further treatment needed', NOW() - INTERVAL '19 day', 4, 'dc_9', 19, 19, '90123456798', '01:20:00'),
       ('Asthma', 'Inhaler', 'Breathing improved', 'Regular inhaler use advised', NOW() - INTERVAL '20 day', 5, 'dc_10', 20, 20, '01234567809', '00:35:00'),
       ('Ear infection', 'Antibiotics', 'Ear pain relieved', 'No further treatment needed', NOW() - INTERVAL '21 day', 1, 'dc_1', 21, 21, '12345678901', '01:10:00'),
       ('Hypertension', 'Medication and diet changes', 'Blood pressure controlled', 'Regular monitoring recommended', NOW() - INTERVAL '22 day', 2, 'dc_2', 22, 22, '23456789012', '00:50:00'),
       ('Fractured leg', 'Casting and physiotherapy', 'Leg healing well', 'Follow up in four weeks', NOW() - INTERVAL '23 day', 3, 'dc_3', 23, 23, '34567890123', '01:30:00'),
       ('Depression', 'Medication and therapy', 'Mood improving', 'Regular counseling sessions advised', NOW() - INTERVAL '24 day', 4, 'dc_4', 24, 24, '45678901234', '00:25:00');

INSERT INTO MedicalProcedure (name, cost, policlinicID)
VALUES ('X-Ray', 100, 1),
       ('Blood Test', 50, 2),
       ('Ultrasound', 150, 3),
       ('MRI', 200, 4),
       ('ECG', 80, 1),
       ('CT Scan', 250, 1),
       ('Endoscopy', 180, 2),
       ('Colonoscopy', 220, 3),
       ('Biopsy', 120, 4),
       ('Allergy Test', 90, 2),
       ('Echocardiogram', 190, 1),
       ('Spirometry', 70, 2),
       ('Bone Density Test', 160, 3),
       ('Electroencephalogram (EEG)', 230, 4),
       ('Mammogram', 140, 3);

INSERT INTO MedicalProcedureTreatment (medicalProcedureID, treatmentID)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5),
       (6, 6),
       (7, 7),
       (8, 8),
       (9, 9),
       (10, 10),
       (11, 11),
       (12, 12),
       (13, 13),
       (14, 14),
       (15, 15);