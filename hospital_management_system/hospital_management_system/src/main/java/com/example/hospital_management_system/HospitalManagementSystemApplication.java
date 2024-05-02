package com.example.hospital_management_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.hospital_management_system.repository.UserRepository;
import com.example.hospital_management_system.entity.User;


@SpringBootApplication
public class HospitalManagementSystemApplication implements CommandLineRunner {
	
	private final UserRepository userRepository;

	@Autowired
	public HospitalManagementSystemApplication(UserRepository userRepository) {
	    this.userRepository = userRepository;
	}



    public static void main(String[] args) {
        SpringApplication.run(HospitalManagementSystemApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user = userRepository.findById(1L).orElse(null);
        
        if (user != null) {
            System.out.println("Kullanıcı bulundu: " + user.getUsername());
        } else {
            System.out.println("Kullanıcı bulunamadı.");
        }
    }
}
