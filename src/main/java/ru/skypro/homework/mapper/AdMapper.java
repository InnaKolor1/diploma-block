package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;

@Mapper(componentModel = "spring")
public interface AdMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "comments", ignore = true)
    AdEntity toEntity(CreateOrUpdateAd createOrUpdateAd);

    @Mapping(target = "phone", source = "author.phone")
    @Mapping(target = "pk", source = "id")
    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "image", expression = "java(entity.getImage() != null ? \"/images/\" + entity.getImage() : null)")
    Ad toDto(AdEntity entity);

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "email", source = "author.email")
    @Mapping(target = "phone", source = "author.phone")
    @Mapping(target = "image", expression = "java(entity.getImage() != null ? \"/images/\" + entity.getImage() : null)")
    ExtendedAd toExtendedAd(AdEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "comments", ignore = true)
    void updateEntityFromDto(CreateOrUpdateAd createOrUpdateAd, AdEntity entity);
}