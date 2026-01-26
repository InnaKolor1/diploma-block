package ru.skypro.homework.mapper.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "role", target = "role")
    User toDto(UserEntity entity);

    @Mapping(target = "id", ignore = true)
    UserEntity toEntity(User dto);
}
