package ru.skypro.homework.service.impl;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void getCurrentUser_ShouldReturnUser_WhenExists() {
        String username = "test@test.com";
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(username);

        User userDto = new User();
        userDto.setEmail(username);

        when(userRepository.findByEmail(username)).thenReturn(Optional.of(userEntity));


        User result = userService.getCurrentUser(username);

        assertNotNull(result);
        assertEquals(username, result.getEmail());
        verify(userRepository, times(1)).findByEmail(username);
    }

    @Test
    void updatePassword_ShouldThrowException_WhenCurrentPasswordIncorrect() {
        String username = "test@test.com";
        String currentPassword = "wrong";
        String newPassword = "newPassword";

        UserEntity userEntity = new UserEntity();
        userEntity.setPassword("encodedPassword");

        when(userRepository.findByEmail(username)).thenReturn(Optional.of(userEntity));
        when(passwordEncoder.matches(currentPassword, "encodedPassword")).thenReturn(false);

        assertThrows(IllegalArgumentException.class,
                () -> userService.updatePassword(username, currentPassword, newPassword));
    }
}