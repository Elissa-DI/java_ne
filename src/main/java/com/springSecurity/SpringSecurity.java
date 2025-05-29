package com.springSecurity;

import com.springSecurity.entities.Role;
import com.springSecurity.entities.User;
import com.springSecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringSecurity implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurity.class, args);
    }

    public void run(String... args) {
        User adminAccount = userRepository.findByRole(Role.ROLE_ADMIN);
        if (null == adminAccount) {
            User user = new User();
            user.setEmail("elissafirstborn@gmail.com");
            user.setFullName("Elissa DUSABE IRADUKUNDA");
            user.setPhoneNumber("0788000000"); // Optional
            user.setNationalId("1199200191023456");
            user.setRole(Role.ROLE_ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("rdowssap"));
            userRepository.save(user);
        }

    }
}
