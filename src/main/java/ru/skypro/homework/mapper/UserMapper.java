package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;

@Component
public abstract class UserMapper {


    public User toDto(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        User user = new User();
        user.setEmail(userEntity.getEmail());
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setPhone(userEntity.getPhone());
        user.setRole(userEntity.getRole());
        if (userEntity.getImage() != null && !userEntity.getImage().isEmpty()) {
            user.setImage("/images/" + userEntity.getImage());
        }

        return user;
    }


    public AdEntity toEntity(CreateOrUpdateAd createOrUpdateAd, Integer authorId, String imageFilename) {
        if (createOrUpdateAd == null) {
            return null;
        }

        AdEntity adEntity = new AdEntity();
        adEntity.setTitle(createOrUpdateAd.getTitle());
        adEntity.setPrice(createOrUpdateAd.getPrice());
        adEntity.setDescription(createOrUpdateAd.getDescription());
        adEntity.setAuthorId(authorId);

        if (imageFilename != null && !imageFilename.isEmpty()) {
            adEntity.setImage(imageFilename);
        }

        return adEntity;
    }


    public UserEntity toEntity(Register register) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(register.getUsername());
        userEntity.setFirstName(register.getFirstName());
        userEntity.setLastName(register.getLastName());
        userEntity.setPhone(register.getPhone());
        userEntity.setRole(register.getRole() != null ? register.getRole() : Role.USER);
        return userEntity;
    }

    public abstract void updateEntityFromDto(UpdateUser updateUser, UserEntity entity);

    public abstract User toDto(AdEntity entity);

    public abstract ExtendedAd toExtendedAd(AdEntity adEntity);
}