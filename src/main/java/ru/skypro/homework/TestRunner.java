package ru.skypro.homework;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class TestRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        log.info("Проверка наличия тестовых пользователей...");

        // тестовый юзер
        createUserIfNotExists("user@gmail.com", "Ося", "Бендер", "+79002223344", "USER");

        // админ
        createUserIfNotExists("admin@gmail.com", "Киса", "Воробьянинов", "+79001112233", "ADMIN");

        log.info("Всего пользователей в базе: {}", userRepository.count());
    }

    private void createUserIfNotExists(String email, String firstName, String lastName,
                                       String phone, String role) {
        Optional<UserEntity> existingUser = userRepository.findByEmail(email);
        if (existingUser.isEmpty()) {
            UserEntity user = new UserEntity(email + "@example.com", "image.jpg", firstName, lastName, 52, 12);
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhone(phone);
            user.setRole(ru.skypro.homework.dto.Role.valueOf(role));
            user.setPassword(passwordEncoder.encode("1234"));

            userRepository.save(user);
            log.info("Создан пользователь: {} / {}", email, "1234");
        } else {
            log.info("Пользователь уже существует: {}", email);
        }
    }
}
