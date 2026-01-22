package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

public interface AdService {
    Ads getAllAds();
    Ad addAd(CreateOrUpdateAd createOrUpdateAd, MultipartFile image, String username);
    ExtendedAd getExtendedAd(Integer id);
    void removeAd(Integer id, String username);
    Ad updateAd(Integer id, CreateOrUpdateAd createOrUpdateAd, String username);
    Ads getAdsByUser(String username);
    void updateAdImage(Integer id, MultipartFile image, String username);

}