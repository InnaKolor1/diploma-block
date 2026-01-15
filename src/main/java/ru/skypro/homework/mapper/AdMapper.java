package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.entity.AdEntity;

@Component
public class AdMapper {


    public Ad toDto(AdEntity adEntity) {
        if (adEntity == null) {
            return null;
        }

        Ad ad = new Ad();
        ad.setPk(Math.toIntExact(adEntity.getId()));
        ad.setTitle(adEntity.getTitle());
        ad.setPrice(adEntity.getPrice());
        ad.setAuthor(adEntity.getAuthorId());

        if (adEntity.getImage() != null && !adEntity.getImage().isEmpty()) {
            ad.setImage("/images/" + adEntity.getImage());
        }

        return ad;
    }


    public AdEntity toEntity(CreateOrUpdateAd createOrUpdateAd, Integer authorId, String imageFilename) {
        if (createOrUpdateAd == null) {
            return null;
        }

        AdEntity adEntity = new AdEntity();
        adEntity.setTitle(createOrUpdateAd.getTitle());
        adEntity.setPrice(createOrUpdateAd.getPrice());
        adEntity.setDescription(createOrUpdateAd.getDescription());
        adEntity.setAuthorId(authorId);

        if (imageFilename != null && !imageFilename.isEmpty()) {
            adEntity.setImage(imageFilename);
        }

        return adEntity;
    }


}