package ru.skypro.homework.service;

import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

public interface UserService {

    User getCurrentUser();


    User getCurrentUser(String username);
    User updateUser(String username, UpdateUser updateUser);
    void updatePassword(String username, String currentPassword, String newPassword);
    void updateUserImage(String username);
    UserEntity getUserEntity(String username);
}