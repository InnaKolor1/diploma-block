package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;

public interface AdMapper {
    static Ad toDto(AdEntity savedAd) {
        return toDto(savedAd);
    }

    Ad toDto(AdEntity entity, UserEntity author);

    ExtendedAd toExtendedAd(AdEntity entity);

}