package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Bean;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "email", source = "username   ")
    @Bean
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "image", constant = "default.jpg")
    @Mapping(target = "phone", source = "phone")
    UserEntity toEntity(Register register);

    @Bean
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "role", ignore = true)
    UserEntity toEntity(UpdateUser updateUser);

    @Bean
    @Mapping(target = "toDto", source = "entity")
    @Mapping(target = "image", expression = "java(entity.getImage() != null ? \"/images/\" + entity.getImage() : null)")
    User toDto(UserEntity entity);

    @Bean
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", constant = "1234")
    @Mapping(target = "image", constant = "default.jpg")
    @Mapping(target = "role", constant = "USER")
    UserEntity toEntity(User user);
}
