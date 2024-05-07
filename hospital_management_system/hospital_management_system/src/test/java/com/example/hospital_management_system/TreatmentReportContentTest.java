package com.example.hospital_management_system;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.table.DefaultTableModel;

import org.junit.jupiter.api.Test;

import ui.ViewPatientAndAddRecordPage;

class TreatmentReportContentTest {

	@Test
	public void testReportContentContainsDayWord() {
		boolean result = ViewPatientAndAddRecordPage.testReportContentContainsDayWord("Hasta 3 gün dinlenmelidir.");
		assertThat(result).as("Rapor içeriği 'gün' kelimesini içermelidir.").isTrue();
	}

	@Test
	public void testReportContentContainsNumberInfo() {
		boolean result = ViewPatientAndAddRecordPage.testReportContentContainsNumberInfo("Hasta 3 gün dinlenmelidir.");
		assertThat(result).as("Rapor içeriği bir sayı bilgisi içermelidir.").isTrue();
	}
}
