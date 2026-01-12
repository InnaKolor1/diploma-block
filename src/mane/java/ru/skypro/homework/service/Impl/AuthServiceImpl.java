package ru.skypro.homework.service.Impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean login(String userName, String password) {
        return userRepository.findByEmail(userName)
                .map(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElse(false);
    }

    @Override
    public boolean register(Register register) {
        if (userRepository.existsByEmail(register.getUsername())) {
            log.warn("Пользователь с email {} уже существует", register.getUsername());
            return false;
        }

        UserEntity user = new UserEntity();
        user.setEmail(register.getUsername());
        user.setPassword(passwordEncoder.encode(register.getPassword()));
        user.setFirstName(register.getFirstName());
        user.setLastName(register.getLastName());
        user.setPhone(register.getPhone());
        user.setRole(register.getRole() != null ? register.getRole() : Role.USER);
        user.setImage(null);

        userRepository.save(user);
        log.info("Зарегистрирован новый пользователь: {}", register.getUsername());
        return true;
    }
}