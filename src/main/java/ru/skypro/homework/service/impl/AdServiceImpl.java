package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.mapper.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final AdMapper adMapper;

    @Override
    public Ads getAllAds() {
        return (Ads) adRepository.findAll()
                .stream()
                .map(adMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Ad addAd(CreateOrUpdateAd createOrUpdateAd, MultipartFile image, String username) {
        return null;
    }

    @Override
    public ExtendedAdDto getExtendedAd(Integer id) {
        return null;
    }

    @Override
    public void removeAd(Integer id, String username) {

    }

    @Override
    public Ad addAd(CreateOrUpdateAd dto, String username) {
        return null;
    }

    @Override
    public void deleteAd(Integer adId, String username) {

    }

    @Override
    public Ad updateAd(Integer id, CreateOrUpdateAd createOrUpdateAd, String username) {
        return null;
    }

    @Override
    public Ads getAdsByUser(String username) {
        return null;
    }

    @Override
    public void updateAdImage(Integer id, MultipartFile image, String username) {

    }

    @Override
    public Ads getAdsMe(String username) {
        return null;
    }

    @Override
    public Ad getAd(Integer id) {
        return adRepository.findById(id)
                .map(adMapper::toDto)
                .orElse(null);
    }
}
