package com.example.hospital_management_system;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;

import ui.RegistrarPage;

class RegistrarPageTest {

		@Test
		public void testEmailContent( ) {
			boolean result = RegistrarPage.checkEmailFormat("melihtunaipek.mti@gmail.com.tr");
	        assertThat(result).as("Email ***@***.com*** formatında olmalıdır.").isTrue();
		}
		
		@Test
		public void testSocialNoContent( ) {
			boolean result = RegistrarPage.checkSocialNo("12345678911");
	        assertThat(result).as("Kimlik numarası 11 haneli olacak şekilde numaralardan oluşmalıdır.").isTrue();
		}
		
		@Test
		public void testBirthDateContent( ) {
			Date result = RegistrarPage.getBirthDate("29.08.2002");
			assertNotNull(result);
		}
	
}
