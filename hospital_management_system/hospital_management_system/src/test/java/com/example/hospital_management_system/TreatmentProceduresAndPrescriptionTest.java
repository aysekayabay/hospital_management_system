package com.example.hospital_management_system;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.table.DefaultTableModel;

import org.junit.jupiter.api.Test;

import ui.ViewPatientAndAddRecordPage;

class TreatmentProceduresAndPrescriptionTest {

	@Test
	void testRequiredProcedAdded() {
		// Fake data oluşturma
		Object[][] fakeData = { { "Procedure 1" }, };

		// Fake model oluşturma
		DefaultTableModel fakeModel = new DefaultTableModel(fakeData, new String[] { "Procedure Name" });

		// Fonksiyonu çağırma ve sonucu kontrol etme
		boolean result = ViewPatientAndAddRecordPage.testRequiredProcedAdded(fakeModel);
		assertTrue(result);
	}

	@Test
	void testPrescriptionNotEmpty() {
		String prescription = "Parol";
		// Fonksiyonu çağırma ve sonucu kontrol etme
		boolean result = ViewPatientAndAddRecordPage.testPrescriptionNotEmpty(prescription);
		assertTrue(result);
	}

}
