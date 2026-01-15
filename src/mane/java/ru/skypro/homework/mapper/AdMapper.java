package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;

@Component
public class AdMapper {

    public AdEntity toEntity(CreateOrUpdateAd dto) {
        AdEntity entity = new AdEntity();
        entity.setTitle(dto.getTitle());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    public Ad toDto(AdEntity entity) {
        Ad dto = new Ad();
        dto.setPk(entity.getId());
        if (entity.getAuthor() != null) {
            dto.setAuthor(entity.getAuthor().getId());
        }
        dto.setImage(entity.getImagePath());
        dto.setPrice(entity.getPrice());
        dto.setTitle(entity.getTitle());
        return dto;
    }

    public ExtendedAd toExtendedDto(AdEntity entity) {
        ExtendedAd dto = new ExtendedAd();
        dto.setPk(entity.getId());
        dto.setPrice(entity.getPrice());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setImage(entity.getImagePath());

        UserEntity author = entity.getAuthor();
        if (author != null) {
            dto.setAuthorFirstName(author.getFirstName());
            dto.setAuthorLastName(author.getLastName());
            dto.setEmail(author.getEmail());
            dto.setPhone(author.getPhone());
        }

        return dto;
    }
}