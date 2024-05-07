-- InsuranceServer tablosuna veri ekleme
INSERT INTO InsuranceServer (name, peopleWithInsurance)
VALUES 
    ('Sigorta Şirketi 1', ARRAY['123456789', '987654321', '111111111', '222222222', '333333333', '444444444', '555555555', '666666666', '777777777', '888888888']),
    ('Sigorta Şirketi 2', ARRAY['456789123', '789123456', '999999999', '000000000', '121212121', '343434343', '565656565', '787878787', '909090909', '232323232']);

-- Policlinic tablosuna veri ekleme
INSERT INTO Policlinic (name, location, collaboratedInsuranceServerID)
VALUES 
    ('Sağlık Polikliniği', 'Merkez Mahallesi', 1),
    ('Özel Tıp Merkezi', 'Göztepe Caddesi', 2);

-- Clinic tablosuna veri ekleme
INSERT INTO Clinic (name, policlinicID)
VALUES 
    ('Göz Kliniği', 1), 
    ('Üroloji Kliniği', 1),
    ('Ortopedi Kliniği', 1),
    ('Psikiyatri Kliniği', 1);

-- Staff tablosuna veri ekleme
INSERT INTO Staff (firstName, lastName, address, staffType, policlinicID, password)
VALUES 
    ('Selim', 'Akın', 'İstanbul', 'treasurer', 1, 'staff1'),
    ('Hüseyin', 'Kaya', 'İstanbul', 'treasurer', 1, 'staff2'),
    ('Yavuz', 'Kartal', 'İstanbul', 'registar', 1, 'staff3'),
    ('Zeynep', 'Çelik', 'İstanbul', 'registar', 1, 'staff4'),
    ('Ali', 'Yıldırım', 'İstanbul', 'information_desk', 1, 'staff5'),
    ('Selin', 'Koç', 'İstanbul', 'information_desk', 1, 'staff6');
	
-- Doctor tablosuna veri ekleme
INSERT INTO Doctor (firstName, lastName, address, specialty, qualification, workingHours, clinicID, password)
VALUES 
   ('Sinan', 'Engin', 'İstanbul', 'Göz Doktoru', 'Uzm. Dr.', '{"Pazartesi": "09:00-17:00", "Salı": "09:00-17:00"}', 1, 'doctor1'),
    ('İbrahim', 'Koral', 'İstanbul', 'Ürolog', 'Uzm. Dr.', '{"Çarşamba": "10:00-18:00", "Perşembe": "10:00-18:00"}', 2, 'doctor2'),
    ('Mustafa', 'Uğurlu', 'İstanbul', 'Ortopedi Uzmanı', 'Uzm. Dr.', '{"Cuma": "08:00-16:00", "Cumartesi": "08:00-12:00"}', 3, 'doctor3'),
    ('Selma', 'Alkış', 'İstanbul', 'Psikiyatrist', 'Uzm. Dr.', '{"Pazartesi": "13:00-19:00", "Salı": "13:00-19:00"}', 4, 'doctor4');

-- Patient tablosuna veri ekleme
INSERT INTO Patient (socialNumber, firstName, lastName, gender, address, birthDate, email, phone)
VALUES 
    ('12345678901', 'Ahmet', 'Yılmaz', 'Erkek', 'İstanbul', '1990-05-15', 'ahmet@example.com', '1234567890'),
    ('23456789012', 'Ayşe', 'Kaya', 'Kadın', 'Ankara', '1985-10-20', 'ayse@example.com', '2345678901'),
    ('34567890123', 'Mehmet', 'Demir', 'Erkek', 'İzmir', '1978-03-25', 'mehmet@example.com', '3456789012'),
    ('45678901234', 'Fatma', 'Arslan', 'Kadın', 'Bursa', '2000-12-10', 'fatma@example.com', '4567890123'),
    ('56789012345', 'Zeynep', 'Çelik', 'Kadın', 'Antalya', '1982-09-03', 'zeynep@example.com', '5678901234'),
    ('67890123456', 'Ali', 'Yıldırım', 'Erkek', 'Trabzon', '1995-08-17', 'ali@example.com', '6789012345'),
    ('78901234567', 'Selin', 'Koç', 'Kadın', 'İzmir', '1975-06-28', 'selin@example.com', '7890123456'),
    ('89012345678', 'Mustafa', 'Ak', 'Erkek', 'Ankara', '1992-04-12', 'mustafa@example.com', '8901234567'),
    ('90123456789', 'Aslı', 'Yılmaz', 'Kadın', 'Bursa', '1988-11-05', 'asli@example.com', '9012345678'),
    ('01234567890', 'Emre', 'Kaya', 'Erkek', 'Antalya', '2005-02-22', 'emre@example.com', '0123456789');
	
-- Payment tablosuna veri ekleme
--INSERT INTO Payment (amount, paymentDate, paymentMethod, patientSocialNumber)
--VALUES 
--    (100.00, CURRENT_TIMESTAMP, 'Kredi Kartı', '12345678901'),
--    (150.00, CURRENT_TIMESTAMP, 'Nakit', '23456789012');

-- Appointment tablosuna veri ekleme
INSERT INTO Appointment (appointmentDate, doctorID, clinicID, patientSocialNumber)
VALUES 
	('2024-05-06 10:00:00', 1, 1, '12345678901');

-- Treatment tablosuna veri ekleme
--INSERT INTO Treatment (diagnosis, prescription, report, referralNote, treatmentDate, clinicID, doctorID, paymentID, appointmentID, patientSocialNumber, treatmentDuration)
--VALUES 

-- MedicalProcedure tablosuna veri ekleme
INSERT INTO MedicalProcedure (name, cost, policlinicID)
VALUES 
    ('Göz Muayenesi', 150, 1),
    ('Ürolojik Ultrason', 250, 1),
    ('Ortopedik Muayene', 200, 1),
    ('Psikiyatrik Değerlendirme', 300, 1),
    ('Katarakt Ameliyatı', 1000, 1),
    ('Böbrek Taşı Kırma', 500, 1),
    ('Kemik Kırığı Tedavisi', 800, 1),
    ('Depresyon Tedavisi', 400, 1),
    ('Göz Lazer Ameliyatı', 2000, 1),
    ('Prostat Kontrolü', 350, 1),
    ('Kulak Burun Boğaz Muayenesi', 180, 1),
    ('Kolit Tedavisi', 600, 1),
    ('Omurga MR', 700, 1),
    ('Bipolar Bozukluk Tedavisi', 450, 1),
    ('Diyabet Kontrolü', 250, 1),
    ('Mesane İdrar Testi', 120, 1),
    ('Kırık Platin Tedavisi', 850, 1),
    ('Anksiyete Terapisi', 380, 1),
    ('Göz Katarakt Kontrolü', 180, 1),
    ('Üretral Darlık Testi', 300, 1);

-- MedicalProcedureTreatment tablosuna veri ekleme
--INSERT INTO MedicalProcedureTreatment (medicalProcedureID, treatmentID)
--VALUES 
--    (1, 1),
--    (2, 2);
