package ru.skypro.homework.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.skypro.homework.entity.Role;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;

@Component
@Slf4j
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        if (userRepository.findByEmail("user@gmail.com").isEmpty()) {
            UserEntity user = new UserEntity();
            user.setEmail("user@gmail.com");
            user.setPassword(passwordEncoder.encode("1234"));
            user.setFirstName("Ося");
            user.setLastName("Бендер");
            user.setPhone("+79002223344");
            user.setRole(Role.USER);

            userRepository.save(user);
            log.info("Создан тестовый пользователь: user@gmail.com / 1234");
        }

        if (userRepository.findByEmail("admin@gmail.com").isEmpty()) {
            UserEntity admin = new UserEntity();
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("1234"));
            admin.setFirstName("Киса");
            admin.setLastName("Воробьянинов");
            admin.setPhone("+79001112233");
            admin.setRole(Role.ADMIN);

            userRepository.save(admin);
            log.info("Создан администратор: admin@gmail.com / 1234");
        }
    }
}