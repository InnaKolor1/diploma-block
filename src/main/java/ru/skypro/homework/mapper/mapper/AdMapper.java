package ru.skypro.homework.mapper.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.entity.AdEntity;

@Mapper(componentModel = "spring")
public interface AdMapper {

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.id", target = "author")
    @Mapping(target = "image", expression = "java(entity.getImage() != null ? \"/images/\" + entity.getImage() : null)")
    Ad toDto(AdEntity entity);

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.id", target = "authorId")
    @Mapping(target = "image", expression = "java(entity.getImage() != null ? \"/images/\" + entity.getImage() : null)")
    @Mapping(target = "phone", expression = "java(entity.getAuthor().getPhone())")
    @Mapping(target = "email", expression = "java(entity.getAuthor().getEmail())")
    @Mapping(target = "authorFirstName", expression = "java(entity.getAuthor().getUsername())")
    @Mapping(target = "authorLastName", ignore = true)
    ExtendedAdDto toExtendedDto(AdEntity entity);
}
