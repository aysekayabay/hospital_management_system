package com.example.hospital_management_system;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ui.*;

public class LoginPageTests {

    @Test
    public void testID() {
        // LoginPage sınıfını oluştur
        LoginPage loginPage = new LoginPage(null); // HospitalManagementService parametresini null olarak geçiyorum çünkü bu testte kullanmayacağım
        
        // 3 karakterden kısa bir ID girdisi
        loginPage.getLogin_id_txtArea().setText("Dr"); // 2 karakter
        assertFalse(loginPage.getLogin_id_txtArea().getText().length() >= 3);
        
        // 3 karakter veya daha uzun bir ID girdisi
        loginPage.getLogin_id_txtArea().setText("Dr1"); // 3 karakter
        assertTrue(loginPage.getLogin_id_txtArea().getText().length() >= 3);
    }

}
