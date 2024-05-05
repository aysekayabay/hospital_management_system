-- ENUM tipini oluştur
CREATE TYPE StaffType AS ENUM ('treasurer', 'registar', 'information desk');

-- InsuranceServer tablosu
CREATE TABLE InsuranceServer (
    id SERIAL PRIMARY KEY,
    name VARCHAR(25) NOT NULL,
    peopleWithInsurance VARCHAR(25)[]
);

-- Policlinic tablosu
CREATE TABLE Policlinic (
    id SERIAL PRIMARY KEY,
    name VARCHAR(25) NOT NULL,
    location VARCHAR(100) NOT NULL,
    collaboratedInsuranceServerID INT REFERENCES InsuranceServer(id)
);

-- Clinic tablosu
CREATE TABLE Clinic (
    id SERIAL PRIMARY KEY,
    name VARCHAR(25) NOT NULL,
    policlinicID INT REFERENCES Policlinic(id) NOT NULL
);

-- Staff tablosu
CREATE TABLE Staff (
    id SERIAL PRIMARY KEY,
    password VARCHAR(25) NOT NULL,
    firstName VARCHAR(25) NOT NULL,
    lastName VARCHAR(25) NOT NULL,
    address VARCHAR(100) NOT NULL,
    staffType StaffType NOT NULL,
    policlinicID INT REFERENCES Policlinic(id) NOT NULL
);

-- Doctor tablosu
CREATE TABLE Doctor (
    id SERIAL PRIMARY KEY,
    password VARCHAR(25) NOT NULL,
    firstName VARCHAR(25) NOT NULL,
    lastName VARCHAR(25) NOT NULL,
    address VARCHAR(100) NOT NULL,
    specialty VARCHAR(50) NOT NULL,
    qualification VARCHAR(50) NOT NULL,
    workingHours JSON NOT NULL,
    clinicID INT REFERENCES Clinic(id) NOT NULL
);

-- Patient tablosu
CREATE TABLE Patient (
    socialNumber VARCHAR(11) PRIMARY KEY,
    firstName VARCHAR(25) NOT NULL,
    lastName VARCHAR(25) NOT NULL,
    gender VARCHAR(25) NOT NULL,
    address VARCHAR(50) NOT NULL,
    birthDate DATE NOT NULL,
    email VARCHAR(25) NOT NULL,
    phone VARCHAR(11) NOT NULL
);

-- Payment tablosu
CREATE TABLE Payment (
    id SERIAL PRIMARY KEY,
    amount DOUBLE PRECISION NOT NULL,
    paymentDate TIMESTAMP NOT NULL,
    paymentMethod VARCHAR(25) NOT NULL,
    patientSocialNumber VARCHAR(11) REFERENCES Patient(socialNumber) NOT NULL
);

-- Appointment tablosu
CREATE TABLE Appointment (
    id SERIAL PRIMARY KEY,
    appointmentDate TIMESTAMP NOT NULL,
    doctorID INT REFERENCES Doctor(id) NOT NULL,
    clinicID INT REFERENCES Clinic(id) NOT NULL,
    patientSocialNumber VARCHAR(11) REFERENCES Patient(socialNumber) NOT NULL
);

-- Treatment tablosu
CREATE TABLE Treatment (
    id SERIAL PRIMARY KEY,
    diagnosis VARCHAR(50) NOT NULL,
    prescription VARCHAR(255),
    report VARCHAR(255) NOT NULL,
    referralNote VARCHAR(255) NOT NULL,
    treatmentDate TIMESTAMP NOT NULL,
    clinicID INT REFERENCES Clinic(id) NOT NULL,
    doctorID INT REFERENCES Doctor(id) NOT NULL,
    paymentID INT REFERENCES Payment(id),
    appointmentID INT REFERENCES Appointment(id) NOT NULL,
    patientSocialNumber VARCHAR(11) REFERENCES Patient(socialNumber) NOT NULL,
    treatmentDuration TIME NOT NULL
);

-- MedicalProcedure tablosu
CREATE TABLE MedicalProcedure (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    cost INT NOT NULL,
    policlinicID INT REFERENCES Policlinic(id) NOT NULL
);

-- MedicalProcedureTreatment tablosu
CREATE TABLE MedicalProcedureTreatment (
    medicalProcedureID INT REFERENCES MedicalProcedure(id) NOT NULL,
    treatmentID INT REFERENCES Treatment(id) NOT NULL,
    PRIMARY KEY (medicalProcedureID, treatmentID)
);