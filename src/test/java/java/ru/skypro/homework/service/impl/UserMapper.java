package java.ru.skypro.homework.service.impl;

import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;

public abstract class UserMapper extends ru.skypro.homework.service.UserMapper {
    public abstract UserEntity toEntity(Register register);

    public abstract void updateEntityFromDto(UpdateUser updateUser, UserEntity entity);

    public abstract User toDto(AdEntity entity);

    public abstract ExtendedAd toExtendedAd(AdEntity adEntity);

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
        user.setRole(userEntity.getRole());
        user.setImage(userEntity.getImage());

        return user;
    }
}
