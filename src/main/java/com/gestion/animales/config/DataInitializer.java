package com.gestion.animales.config;

import com.gestion.animales.models.AppUser;
import com.gestion.animales.models.Role;
import com.gestion.animales.repositories.AppUserRepository;
import com.gestion.animales.repositories.RoleRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
@Profile("dev")
public class DataInitializer {

    @Bean
    CommandLineRunner initData(
            RoleRepository roleRepository,
            AppUserRepository userRepository,
            PasswordEncoder encoder) {

        return args -> {

            // Crear roles si no existen
            Role userRole = roleRepository
                    .findByName("ROLE_USER")
                    .orElseGet(() -> roleRepository.save(
                            new Role("ROLE_USER")
                    ));

            Role adminRole = roleRepository
                    .findByName("ROLE_ADMIN")
                    .orElseGet(() -> roleRepository.save(
                            new Role("ROLE_ADMIN")
                    ));

            // Crear usuario USER si no existe
            if (userRepository.findByEmail(
                    "usuario1@ejemplo.test").isEmpty()) {

                AppUser user = new AppUser();
                user.setEmail("usuario1@ejemplo.test");
                user.setPassword(encoder.encode("User123!"));
                user.setRoles(Set.of(userRole));

                userRepository.save(user);
            }

            // Crear usuario ADMIN si no existe
            if (userRepository.findByEmail(
                    "admin@ejemplo.test").isEmpty()) {

                AppUser admin = new AppUser();
                admin.setEmail("admin@ejemplo.test");
                admin.setPassword(encoder.encode("Admin123!"));
                admin.setRoles(Set.of(adminRole));

                userRepository.save(admin);
            }
        };
    }
}

