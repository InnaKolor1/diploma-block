package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

@SuppressWarnings("MapstructReferenceInspection")
@Mapper()
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "image", source = "image")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "comments", source = "comments")
    @Mapping(target = "authorities", source = "authorities")
    @Mapping(target = "ads", source = "ads")
    default void toEntity(Register register) {

    }

    @Mapping(target = "email", source = "username")
    default void updateEntity(UpdateUser updateUser, UserEntity entity) {

    }

    @Mapping(target = "email", source = "username")
    User toDto(UserEntity entity);
}
