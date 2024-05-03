-- InsuranceServer tablosuna veri ekleme
INSERT INTO InsuranceServer (name, peopleWithInsurance)
VALUES 
    ('InsuranceCo1', ARRAY['John', 'Alice']),
    ('InsuranceCo2', ARRAY['Bob', 'Eve']);

-- Policlinic tablosuna veri ekleme
INSERT INTO Policlinic (name, location, collaboratedInsuranceServerID)
VALUES 
    ('Policlinic1', 'Location1', 1),
    ('Policlinic2', 'Location2', 2);

-- Clinic tablosuna veri ekleme
INSERT INTO Clinic (name, policlinicID)
VALUES 
    ('Clinic1', 1),
    ('Clinic2', 2),
    ('Clinic3', 1),
    ('Clinic4', 2),
    ('Clinic5', 1);

-- Staff tablosuna veri ekleme
INSERT INTO Staff (firstName, lastName, address, staffType, policlinicID, password)
VALUES 
    ('John', 'Doe', 'Address1', 'treasurer', 1, 'password1'),
    ('Alice', 'Smith', 'Address2', 'registar', 2, 'password2'),
    ('Bob', 'Johnson', 'Address3', 'information desk', 1, 'password3'),
    ('Eve', 'Clark', 'Address4', 'treasurer', 2, 'password4'),
    ('Charlie', 'Brown', 'Address5', 'information desk', 1, 'password5');


-- Doctor tablosuna veri ekleme
INSERT INTO Doctor (firstName, lastName, address, specialty, qualification, workingHours, clinicID, password)
VALUES 
    ('Dr. Robert', 'Johnson', 'Address6', 'Cardiologist', 'MD', '{"Monday": "9am-5pm", "Tuesday": "9am-5pm"}', 1, 'doctor1'),
    ('Dr. Emily', 'Clark', 'Address7', 'Dermatologist', 'MD', '{"Wednesday": "10am-6pm", "Thursday": "10am-6pm"}', 2, 'doctor2'),
    ('Dr. Michael', 'Williams', 'Address8', 'Pediatrician', 'MD', '{"Friday": "8am-4pm", "Saturday": "8am-12pm"}', 1, 'doctor3'),
    ('Dr. Emma', 'Brown', 'Address9', 'Psychiatrist', 'MD', '{"Monday": "1pm-7pm", "Tuesday": "1pm-7pm"}', 2, 'doctor4'),
    ('Dr. Sarah', 'Lee', 'Address10', 'Surgeon', 'MD', '{"Wednesday": "9am-3pm", "Thursday": "9am-3pm"}', 1, 'doctor5');

-- Patient tablosuna veri ekleme
INSERT INTO Patient (socialNumber, firstName, lastName, gender, address, birthDate, email, phone)
VALUES 
    ('12345678901', 'Michael', 'Williams', 'Male', 'Address11', '1990-05-15', 'michael@example.com', '1234567890'),
    ('23456789012', 'Emma', 'Brown', 'Female', 'Address12', '1985-10-20', 'emma@example.com', '2345678901'),
    ('34567890123', 'Charlie', 'Johnson', 'Male', 'Address13', '1978-03-25', 'charlie@example.com', '3456789012'),
    ('45678901234', 'Lily', 'Miller', 'Female', 'Address14', '2000-12-10', 'lily@example.com', '4567890123'),
    ('56789012345', 'James', 'Anderson', 'Male', 'Address15', '1982-09-03', 'james@example.com', '5678901234');

-- Payment tablosuna veri ekleme
INSERT INTO Payment (amount, paymentDate, paymentMethod, patientSocialNumber)
VALUES 
    (100.00, CURRENT_TIMESTAMP, 'Credit Card', '12345678901'),
    (150.00, CURRENT_TIMESTAMP, 'Cash', '23456789012');

-- Appointment tablosuna veri ekleme
INSERT INTO Appointment (appointmentDate, doctorID, clinicID, patientSocialNumber)
VALUES 
    (CURRENT_TIMESTAMP, 1, 1, '12345678901'),
    (CURRENT_TIMESTAMP, 2, 2, '23456789012');

-- Treatment tablosuna veri ekleme
INSERT INTO Treatment (diagnosis, prescription, report, referralNote, treatmentDate, clinicID, doctorID, paymentID, appointmentID, patientSocialNumber, treatmentDuration)
VALUES 
    ('Flu', 'Medication A', 'Good recovery', 'Referral to specialist', CURRENT_TIMESTAMP, 1, 1, 1, 1, '12345678901', '00:30:00'),
    ('Allergy', 'Medication B', 'Stable condition', 'No referral needed', CURRENT_TIMESTAMP, 2, 2, 2, 2, '23456789012', '01:00:00');

-- MedicalProcedure tablosuna veri ekleme
INSERT INTO MedicalProcedure (name, cost, policlinicID)
VALUES 
    ('Procedure1', 200, 1),
    ('Procedure2', 300, 2);

-- MedicalProcedureTreatment tablosuna veri ekleme
INSERT INTO MedicalProcedureTreatment (medicalProcedureID, treatmentID)
VALUES 
    (1, 1),
    (2, 2);