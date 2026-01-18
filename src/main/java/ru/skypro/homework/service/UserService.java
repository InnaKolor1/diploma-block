package ru.skypro.homework.service;

import ru.skypro.homework.dto.User;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.NewPassword;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.UserEntity;

public interface UserService {
    User getCurrentUser();

    User updateUser(UpdateUser updateUser);

    NewPassword updatePassword(NewPassword newPassword);

    User updateUserImage(MultipartFile image);

    String getCurrentUser(String name);

    void updatePassword(String name, String currentPassword, String newPassword);

    User updateUser(String name, UpdateUser updateUser);

    void updateUserImage(String name, String imagePath);

    UserEntity getUserEntity(String username);
}
