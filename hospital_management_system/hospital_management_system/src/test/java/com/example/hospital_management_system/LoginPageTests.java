package com.example.hospital_management_system;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ui.*;

public class LoginPageTests {

    @Test
    public void testID() {
    	
        boolean result1 = LoginPage.isIdFormatSuitible("141");
        boolean result2 = LoginPage.isIdFormatSuitible("1");
        
        assertThat(result1 || result2).isFalse();
        
    }
}
