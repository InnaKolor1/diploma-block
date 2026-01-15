package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
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
        AdEntity userEnt0ity;
        return userMapper.toDto(userEntity);
    }

    @Override
    public User updateUser(String username, UpdateUser updateUser) {
        UserEntity userEntity = getUserEntity(username);
        userMapper.updateEntityFromDto(updateUser, userEntity);
        userRepository.save(userEntity);
        return userMapper.toDto(userEntity);
    }

    @Override
    public void updateUserImage(String username, String imagePath) {
        UserEntity userEntity = getUserEntity(username);
        userRepository.save(userEntity);
    }

    @Override
    @PreAuthorize("#username == authentication.principal.username")
    public void updatePassword(String username, String currentPassword, String newPassword) {
        UserEntity userEntity = getUserEntity(username);

        if (!passwordEncoder.matches(currentPassword, String.valueOf(userEntity.getClass()))) {
            throw new IllegalArgumentException("Неверный текущий пароль");
        }

    }

    @Override
    public UserEntity getUserEntity(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден: " + username));
    }
}