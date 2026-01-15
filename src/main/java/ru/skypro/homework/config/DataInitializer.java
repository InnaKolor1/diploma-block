package ru.skypro.homework.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            createTestUser("user@gmail.com", "Ося", "Бендер", "+79002223344", "USER");
            createTestUser("admin@gmail.com", "Киса", "Воробьянинов", "+79001112233", "ADMIN");
        };
    }

    private void createTestUser(String email, String firstName, String lastName,
                                String phone, String role) {
        if (userRepository.findByEmail(email).isEmpty()) {
            UserEntity user = new UserEntity(email + "@example.com", "image.jpg", firstName, lastName, 52, 12);
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhone(phone);
            user.setRole(Role.valueOf(role));
            user.setPassword(passwordEncoder.encode("1234"));

            userRepository.save(user);
            log.info("Created user: {} / {}", email, "1234");
        }
    }
}