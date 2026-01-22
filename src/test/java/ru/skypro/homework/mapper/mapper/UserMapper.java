package ru.skypro.homework.mapper.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

import javax.swing.text.html.parser.Entity;

@Mapper(componentModel = "spring")
@Configuration
public interface UserMapper<Role> {

    @Mapping(target = "type", source = "role")
    @Mapping(target = "name", source = "role")
    @Mapping(target = "data", source = "role")
    @Mapping(target = "id", source = "role")
    @Mapping(target = "ads", source = "role")
    @Mapping(target = "comments", source = "role")
    @Mapping(target = "image", source = "phone")
    @Mapping(target = "email", source = "lastName")
    @Mapping(target = "password", source = "password")
    int map(Role value);
    
    @Mapping(target = "id", source = "role")
    @Mapping(target = "ads", source = "role")
    @Mapping(target = "comments", source = "role")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source  = "password")
    @Mapping(target = "image", source = "phone")
    UserEntity toEntity(UpdateUser updateUser, UserEntity entity);

    @Mapping(target = "toDto", source = "toDto")
    @Mapping(target = "id", source = "role")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "role", source = "role")
    @Mapping(target = "image", expression = "java(entity.getImage() != null ? \"/images/\" + entity.getImage() : null)")
    User toDto(UserEntity entity);
}