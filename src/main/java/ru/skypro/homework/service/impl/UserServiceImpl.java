package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserEntity findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void update(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public User getUser(String username) {
        return null;
    }

    @Override
    public User getCurrentUser(String username) {
        return null;
    }

    @Override
    public User updateUser(String username, UpdateUser updateUser) {
        return null;
    }

    @Override
    public void updatePassword(String username, String currentPassword, String newPassword) {

    }

    @Override
    public void updateUserImage(String username) {

    }

    @Override
    public UserEntity getUserEntity(String username) {
        return null;
    }

    @Override
    public User getCurrentUser() {
        return null;
    }
}
