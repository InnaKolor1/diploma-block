package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    void register_ShouldReturnTrue_WhenUserDoesNotExist() {
        Register register = new Register();
        register.setUsername("test@test.com");
        register.setPassword("password");
        register.setFirstName("Test");
        register.setLastName("User");
        register.setPhone("+79990000000");
        register.setRole(Role.USER);

        when(userRepository.existsByEmail(register.getUsername())).thenReturn(false);
        when(passwordEncoder.encode(any())).thenReturn("encodedPassword");
        when(userRepository.save(any(UserEntity.class))).thenReturn(new UserEntity());

        boolean result = authService.register(register);

        assertTrue(result);
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    void register_ShouldReturnFalse_WhenUserExists() {
        Register register = new Register();
        register.setUsername("existing@test.com");

        when(userRepository.existsByEmail(register.getUsername())).thenReturn(true);

        boolean result = authService.register(register);

        assertFalse(result);
        verify(userRepository, never()).save(any());
    }
}