package com.myorganisation.traceboard.service;

import com.myorganisation.traceboard.model.User;
import com.myorganisation.traceboard.model.enums.UserRole;
import com.myorganisation.traceboard.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CreateTestAdminUserService {

    @Bean
    public CommandLineRunner createAdminUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if(userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();

                admin.setName("Abhishek Gupta");
                admin.setPhone("8840204441");
                admin.setEmail("abhishekg.developer@gmail.com");
                admin.setRole(UserRole.ADMIN);
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("root"));
                admin.setPhotoId(null);

                userRepository.save(admin);
                System.out.println("User: admin created");
            }
        };
    }
}
