package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getCurrentUser(String username) {
        UserEntity userEntity = getUserEntity(username);
        return userMapper.toDto(userEntity);
    }

    @Override
    public User updateUser(String username, UpdateUser updateUser) {
        UserEntity userEntity = getUserEntity(username);
        userMapper.updateEntityFromDto(updateUser, userEntity);
        UserEntity updatedUser = userRepository.save(userEntity);
        return userMapper.toDto(updatedUser);
    }

    @Override
    public void updateUserImage(String username, String imagePath) {
        UserEntity userEntity = getUserEntity(username);
        userEntity.setImage(imagePath);
        userRepository.save(userEntity);
    }

    @Override
    @PreAuthorize("#username == authentication.principal.username")
    public void updatePassword(String username, String currentPassword, String newPassword) {
        UserEntity userEntity = getUserEntity(username);

        if (!passwordEncoder.matches(currentPassword, userEntity.getPassword())) {
            throw new IllegalArgumentException("Неверный текущий пароль");
        }

        userEntity.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(userEntity);
    }

    @Override
    public UserEntity getUserEntity(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден: " + username));
    }
}