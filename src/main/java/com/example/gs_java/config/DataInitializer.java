package com.example.gs_java.config;

import com.example.gs_java.model.Administrador;
import com.example.gs_java.model.Role;
import com.example.gs_java.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {

            if (userRepository.findByEmail("admin@123141511.com").isEmpty()) {
                Administrador admin = new Administrador();
                admin.setNomeUser("admin");
                admin.setDataAniversario(LocalDate.of(2000, 1, 1));
                admin.setPassword(passwordEncoder.encode("admin"));
                admin.setCodigo("123141511");
                admin.setEmail("admin@123141511.com");
                admin.setRole(Role.ADMIN);
                userRepository.save(admin);
                System.out.println("✅ Admin salvo: " + admin.getEmail() + " / " + admin.getPassword());
            }
        };
    }

}
