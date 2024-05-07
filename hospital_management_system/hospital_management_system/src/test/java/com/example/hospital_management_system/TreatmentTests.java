package com.example.hospital_management_system;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.table.DefaultTableModel;

import org.junit.jupiter.api.Test;

import ui.ViewPatientAndAddRecordPage;

class TreatmentTests {
	
	@Test
    public void testReportContent() {
        boolean result = ViewPatientAndAddRecordPage.testReportContent("Hasta 3 gün dinlenmelidir.");
        assertThat(result).as("Rapor içeriği bir sayı ve 'gün' kelimesini içermelidir.").isTrue();
    }

	@Test
    void testRequiredProcedAdded() {
        // Fake data oluşturma
        Object[][] fakeData = {
            {"Procedure 1"},
        };

        // Fake model oluşturma
        DefaultTableModel fakeModel = new DefaultTableModel(fakeData, new String[] {"Procedure Name"});
        
        // Fonksiyonu çağırma ve sonucu kontrol etme
        boolean result = ViewPatientAndAddRecordPage.testRequiredProcedAdded(fakeModel);
        assertTrue(result);
    }
}
