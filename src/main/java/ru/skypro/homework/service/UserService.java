package ru.skypro.homework.service;

import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

public interface UserService {
    void updatePassword(String username, String currentPassword, String newPassword);
    User updateUser(String username, UpdateUser updateUser);
    User getCurrentUser(String username);
    void updateUserImage(String username, String imagePath);

    UserEntity getUserEntity(String username);
}