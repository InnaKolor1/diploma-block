package ru.skypro.homework.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;

@SuppressWarnings("MapstructReferenceInspection")
@Mapper()
public interface AdMapper {
    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);

    @org.mapstruct.Mapping(target = "imagePath", source = "imagePath")
    @org.mapstruct.Mapping(target = "id", source = "id")
    @org.mapstruct.Mapping(target = "comments", source = "comments")
    @org.mapstruct.Mapping(target = "author", source = "author")
    default AdEntity toEntity(CreateOrUpdateAd dto) {
        return null;
    }

    @org.mapstruct.Mapping(target = "pk", source = "id")
    @org.mapstruct.Mapping(target = "image", source = "imagePath")
    default Ad toDto(AdEntity entity) {
        return null;
    }

    @org.mapstruct.Mapping(target = "pk", source = "id")
    @org.mapstruct.Mapping(target = "phone", source = "author.phone")
    @org.mapstruct.Mapping(target = "image", source = "imagePath")
    @org.mapstruct.Mapping(target = "email", source = "author.email")
    @org.mapstruct.Mapping(target = "authorLastName", source = "authorLastName")
    @org.mapstruct.Mapping(target = "authorFirstName", source = "authorFirstName")
    default ExtendedAd toExtendedDto(AdEntity entity) {
        return null;
    }
}