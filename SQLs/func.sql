-- Staff için ID oluşturan işlev
CREATE OR REPLACE FUNCTION generate_staff_id() RETURNS TEXT AS $$
DECLARE
    new_id TEXT;
BEGIN
    SELECT CONCAT('st_', COALESCE(MAX(RIGHT(staff_id, -3))::INT + 1, 1)) INTO new_id FROM Staff;
    RETURN new_id;
END;
$$ LANGUAGE plpgsql;

-- Doctor için ID oluşturan işlev
CREATE OR REPLACE FUNCTION generate_doctor_id() RETURNS TEXT AS $$
DECLARE
    new_id TEXT;
BEGIN
    SELECT CONCAT('dc_', COALESCE(MAX(RIGHT(doctor_id, -3))::INT + 1, 1)) INTO new_id FROM Doctor;
    RETURN new_id;
END;
$$ LANGUAGE plpgsql;

-- Staff eklemek için
CREATE OR REPLACE FUNCTION add_staff(
    p_firstName VARCHAR(25),
    p_lastName VARCHAR(25),
    p_address VARCHAR(100),
    p_policlinicID INT
) RETURNS VOID AS $$
BEGIN
    INSERT INTO Staff (staff_id, firstName, lastName, address, policlinicID)
    VALUES (generate_staff_id(), p_firstName, p_lastName, p_address, p_policlinicID);
END;
$$ LANGUAGE plpgsql;

-- Doctor eklemek için
CREATE OR REPLACE FUNCTION add_doctor(
    p_firstName VARCHAR(25),
    p_lastName VARCHAR(25),
    p_address VARCHAR(100),
    p_specialty VARCHAR(50),
    p_qualification VARCHAR(50),
    p_workingHours JSON,
    p_clinicID INT
) RETURNS VOID AS $$
BEGIN
    INSERT INTO Doctor (doctor_id, firstName, lastName, address, specialty, qualification, workingHours, clinicID)
    VALUES (generate_doctor_id(), p_firstName, p_lastName, p_address, p_specialty, p_qualification, p_workingHours, p_clinicID);
END;
$$ LANGUAGE plpgsql;
