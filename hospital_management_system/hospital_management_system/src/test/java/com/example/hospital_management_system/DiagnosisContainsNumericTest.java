package com.example.hospital_management_system;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ui.ViewPatientAndAddRecordPage;

class DiagnosisContainsNumericTest {

	@Test
	void testDiagnosis() {
        boolean result1 = ViewPatientAndAddRecordPage.testDiagnosisContainsNumeric("555 Katarakt");
        assertThat(result1).as("Tanı alanı sayısal değer içeremez.").isFalse();
	}

}
