package com.example.hospital_management_system;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ui.ViewPatientAndAddRecordPage;

class DiagnosisEmptyTest {

	@Test
    public void testDiagnosis() {
        boolean result1 = ViewPatientAndAddRecordPage.testDiagnosisEmpty(" ");
        assertThat(result1).as("Tanı alanı boş bırakılamaz.").isFalse();
    }

}
