package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;

@Component
public class AdMapper {

    public Ad toDto(AdEntity entity) {
        if (entity == null) {
            return null;
        }

        Ad ad = new Ad();
        ad.setPk(entity.getId());
        ad.setTitle(entity.getTitle());
        ad.setPrice(entity.getPrice());
        ad.setDescription(entity.getDescription());
        ad.setImage(entity.getImage() != null ? "/images/" + entity.getImage() : null);

        if (entity.getAuthor() != null) {
            ad.setAuthor(entity.getAuthor().getId());
        }

        return ad;
    }

    public ExtendedAd toExtendedAd(AdEntity entity) {
        if (entity == null) {
            return null;
        }

        ExtendedAd extendedAd = new ExtendedAd();
        extendedAd.setPk(entity.getId());
        extendedAd.setTitle(entity.getTitle());
        extendedAd.setPrice(entity.getPrice());
        extendedAd.setDescription(entity.getDescription());
        extendedAd.setImage(entity.getImage() != null ? "/images/" + entity.getImage() : null);

        if (entity.getAuthor() != null) {
            extendedAd.setAuthorFirstName(entity.getAuthor().getFirstName());
            extendedAd.setAuthorLastName(entity.getAuthor().getLastName());
            extendedAd.setEmail(entity.getAuthor().getEmail());
            extendedAd.setPhone(entity.getAuthor().getPhone());
        }

        return extendedAd;
    }
}