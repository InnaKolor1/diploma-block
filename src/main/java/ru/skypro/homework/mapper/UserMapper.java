package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;

@Component
public abstract class UserMapper {

    public UserEntity toEntity(Register register) {
        if (register == null) {
            return null;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.getEmail(register.getUsername());
        userEntity.setFirstName(register.getFirstName());
        userEntity.setLastName(register.getLastName());
        userEntity.setPhone(register.getPhone());
        userEntity.setRole(Role.USER);

        return userEntity;
    }
    public User toDto(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        User user = new User();
        user.setId(userEntity.getId());
        user.setEmail(userEntity.getEmail());
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setPhone(userEntity.getPhone());
        user.setRole(ru.skypro.homework.dto.Role.valueOf(userEntity.getRole()));

        if (userEntity.getImage() != null && !userEntity.getImage().isEmpty()) {
            user.setImage("/images/" + userEntity.getImage());
        }

        return user;
    }

    public Object UserEntity(Register register) {

        if (register == null) {
            return null;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(register.getUsername());
        userEntity.setFirstName(register.getFirstName());
        userEntity.setLastName(register.getLastName());
        userEntity.setPhone(register.getPhone());
        userEntity.setRole(Role.valueOf(register.getRole().name()));

        return userEntity;
    }


    public void updateEntity(UpdateUser updateUser, UserEntity userEntity) {
        if (updateUser == null || userEntity == null) {
            return;
        }

        if (updateUser.getFirstName() != null &&
                !updateUser.getFirstName().trim().isEmpty()) {
            userEntity.setFirstName(updateUser.getFirstName());
        }

        if (updateUser.getLastName() != null &&
                !updateUser.getLastName().trim().isEmpty()) {
            userEntity.setLastName(updateUser.getLastName());
        }

        if (updateUser.getPhone() != null &&
                !updateUser.getPhone().trim().isEmpty()) {
            userEntity.setPhone(updateUser.getPhone());
        }
    }

    public abstract void updateEntityFromDto(UpdateUser updateUser, UserEntity entity);

    public abstract User toDto(AdEntity entity);

    public abstract ExtendedAd toExtendedAd(AdEntity adEntity);
}