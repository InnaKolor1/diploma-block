package ru.skypro.homework.service.impl;


import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

@Getter
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getCurrentUser() {
        return null;
    }

    @Override
    public User updateUser(UpdateUser updateUser) {
        User user = new User();
        user.setFirstName(updateUser.getFirstName());
        user.setLastName(updateUser.getLastName());
        user.setPhone(updateUser.getPhone());
        return user;
    }

    @Override
    public NewPassword updatePassword(NewPassword newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword.getNewPassword());
        newPassword.setNewPassword(encodedPassword);
        return newPassword;
    }

    @Override
    public String getCurrentUser(String name) {
        return name;
    }

    @Override
    public void updatePassword(String name, String currentPassword, String newPassword) {
    }

    @Override
    public User updateUser(String name, UpdateUser updateUser) {
        User user = new User();
        user.setFirstName(updateUser.getFirstName());
        user.setLastName(updateUser.getLastName());
        user.setPhone(updateUser.getPhone());
        return user;
    }

    @Override
    public void updateUserImage(String name, String imagePath) {

    }

    @Override
    public UserEntity getUserEntity(String username) {
        return null;
    }

    @Override
    public User updateUserImage(MultipartFile image) {
        return updateUserImage(image);
    }

}