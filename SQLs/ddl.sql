CREATE TYPE StaffType AS ENUM ('treasurer', 'registar', 'information desk');

CREATE TABLE InsuranceServer (
	id SERIAL PRIMARY KEY,
	name VARCHAR(25) NOT NULL,
	peopleWithInsurance VARCHAR(25)[]
);

CREATE TABLE Policlinic (
	id SERIAL PRIMARY KEY,
	name VARCHAR(25) NOT NULL,
	location VARCHAR(100) NOT NULL,
	collabratedInsuranceServerID INT REFERENCES InsuranceServer(id)
);

CREATE TABLE Clinic (
	id SERIAL PRIMARY KEY,
	name VARCHAR(25) NOT NULL,
	policlinicID INT REFERENCES Policlinic(id) NOT NULL
);

CREATE TABLE Staff (
	id VARCHAR(11) PRIMARY KEY,
	firstName VARCHAR(25) NOT NULL,
	lastName VARCHAR(25) NOT NULL,
	address VARCHAR(100) NOT NULL,
	policlinicID INT REFERENCES Policlinic(id) NOT NULL
);

CREATE TABLE Doctor (
    id VARCHAR(11) PRIMARY KEY,
	firstName VARCHAR(25) NOT NULL,
	lastName VARCHAR(25) NOT NULL,
	address VARCHAR(100) NOT NULL,
    specialty VARCHAR(50) NOT NULL,
	qualifitacion VARCHAR(50) NOT NULL,
    workingHours JSON NOT NULL,
	clinicID INT REFERENCES Clinic(id) NOT NULL
);

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

CREATE TABLE Payment (
    id SERIAL PRIMARY KEY,
	amount DOUBLE PRECISION NOT NULL,
	paymentDate TIMESTAMP NOT NULL,
	paymentMethod VARCHAR(25) NOT NULL,
	patientSocialNumber VARCHAR(11) REFERENCES Patient(socialNumber) NOT NULL
);

CREATE TABLE Appointment (
	id SERIAL PRIMARY KEY,
	appointmentDate TIMESTAMP NOT NULL,
	doctorID VARCHAR(11) REFERENCES Doctor(id) NOT NULL,
	clinicID INT REFERENCES Clinic(id) NOT NULL,
	patientsocialNumber VARCHAR(11) REFERENCES Patient(socialNumber) NOT NULL
);

CREATE TABLE Treatment (
	id SERIAL PRIMARY KEY,
	diagnosis VARCHAR(50) NOT NULL,
	prescription VARCHAR(255),
	report VARCHAR(255) NOT NULL,
	refferalNote VARCHAR(255) NOT NULL,
	treatmentDate TIMESTAMP NOT NULL,
	clinicID INT REFERENCES Clinic(id) NOT NULL,
	doctorID VARCHAR(11) REFERENCES Doctor(id) NOT NULL,
	paymentID INT REFERENCES Payment(id) NOT NULL,
	appointmentID INT REFERENCES Appointment(id) NOT NULL,
	patientsocialNumber VARCHAR(11) REFERENCES Patient(socialNumber) NOT NULL,
	treatmentDuration TIME NOT NULL
);

CREATE TABLE MedicalProcedure (
	id SERIAL PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	cost INT NOT NULL,
	policlinicID INT REFERENCES Policlinic(id) NOT NULL
);

CREATE TABLE MedicalProcedureTreatment (
    medicalProcedureID INT REFERENCES medicalProcedure(id) NOT NULL,
    treatmentID INT REFERENCES treatment(id) NOT NULL,
    PRIMARY KEY (medicalProcedureID, treatmentID)
);