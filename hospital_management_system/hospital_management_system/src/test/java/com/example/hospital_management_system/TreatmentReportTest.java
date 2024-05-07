package com.example.hospital_management_system;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ui.ViewPatientAndAddRecordPage;

class TreatmentReportTest {
	
	@Test
    public void testReportContent() {
        boolean result = ViewPatientAndAddRecordPage.testReportContent("Hasta 3 gün dinlenmelidir.");
        assertThat(result).as("Rapor içeriği bir sayı ve 'gün' kelimesini içermelidir.").isTrue();
    }

}
