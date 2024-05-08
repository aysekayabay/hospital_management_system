package com.example.hospital_management_system;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import ui.InfoDeskPage;

class InfoDeskPageTest {

	@Test
	void test() {
		//boolean result1 = ViewPatientAndAddRecordPage.testDiagnosisContent("");
        //boolean result2 = ViewPatientAndAddRecordPage.testDiagnosisContent("Katarakt 555");
        //assertThat(result1 || result2).as("Tanı alanı boş bırakılamaz veya sayı içeremez.").isFalse();
        
        boolean result1 = InfoDeskPage.isValidDateFormat("09-12-2024");
        boolean result2 = InfoDeskPage.isValidDateFormat("09/12/2024");
        assertThat(result1 || result2).isFalse();

        
	}

}
