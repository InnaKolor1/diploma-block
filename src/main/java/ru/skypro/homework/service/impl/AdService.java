package ru.skypro.homework.service.impl;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;

public interface AdService {
    Ads getAllAds();

    Ad addAd(CreateOrUpdateAd createOrUpdateAd, MultipartFile image, String username);

    ExtendedAd getExtendedAd(Integer id);

    void removeAd(Integer id, String username);

    Ad updateAd(Integer id, CreateOrUpdateAd createOrUpdateAd, String username);

    Ads getAdsByUser(String username);

    void updateAdImage(Integer id, MultipartFile image, String username);

    AdEntity getAdEntity(Integer id);

    boolean isAdOwner(Integer adId, String username);
}
