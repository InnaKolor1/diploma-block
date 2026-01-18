package ru.skypro.homework.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

@Mapper(componentModel = "spring")
@Configuration
public interface UserMapper{

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ads", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "email", source = "username")
    @Mapping(target = "password", ignore = true)
    UserEntity toEntity(Register register);

    @Mapping(target = "role", source = "role")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ads", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "image", ignore = true)
    void updateEntityFromDto(UpdateUser updateUser, UserEntity entity);

    @Mapping(target = "toDto", source = "toDto")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "role", source = "role")
    @Mapping(target = "image", expression = "java(entity.getImage() != null ? \"/images/\" + entity.getImage() : null)")
    User toDto(UserEntity entity);
}