package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    @Override
    public boolean login(String username, String password) {
        try {
            var userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("Invalid password");
            }
            return true;
        } catch (Exception e) {
            log.error("Login failed for user: {}", username, e);
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @Override
    public boolean register(Register register) {
        if (userRepository.findByEmail(register.getUsername()).isPresent()) {
            log.warn("User already exists: {}", register.getUsername());
            return false;
        }

        try {
            UserEntity userEntity = userMapper.toEntity(register);
            userEntity.setPassword(passwordEncoder.encode(register.getPassword()));
            userEntity.setRole(register.getRole() != null ? register.getRole() : Role.USER);

            userRepository.save(userEntity);
            log.info("User registered successfully: {}", register.getUsername());
            return true;
        } catch (Exception e) {
            log.error("Registration failed for user: {}", register.getUsername(), e);
            return false;
        }
    }
}