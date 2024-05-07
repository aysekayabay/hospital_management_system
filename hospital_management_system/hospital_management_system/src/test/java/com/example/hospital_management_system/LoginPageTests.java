package com.example.hospital_management_system;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ui.*;

public class LoginPageTests {

    @Test
    public void testID() {
    	
        LoginPage loginPage = new LoginPage(null);
        loginPage.getLogin_id_txtArea().setText("Dr");
        assertFalse(loginPage.getLogin_id_txtArea().getText().length() >= 3);
     
        loginPage.getLogin_id_txtArea().setText("Dr1"); 
        assertTrue(loginPage.getLogin_id_txtArea().getText().length() >= 3);
    }

}
