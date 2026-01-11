package ru.skypro.homework.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdService {
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final AdMapper adMapper;
    private final Path imageStoragePath = Paths.get("images/ads");

    public AdService(AdRepository adRepository, UserRepository userRepository, AdMapper adMapper) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.adMapper = adMapper;

        try {
            Files.createDirectories(imageStoragePath);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось создать директорию для хранения изображений", e);
        }
    }

    public Ads getAllAds() {
        List<Ad> ads = adRepository.findAll().stream()
                .map(adMapper::toDto)
                .collect(Collectors.toList());

        Ads result = new Ads();
        result.setCount(ads.size());
        result.setResults(ads);
        return result;
    }

    public Optional<Ad> createAd(CreateOrUpdateAd properties, MultipartFile image) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(username)
                .map(author -> {
                    AdEntity entity = adMapper.toEntity(properties);
                    entity.setAuthor(author);

                    if (image != null && !image.isEmpty()) {
                        String filename = saveImage(image);
                        entity.setImagePath("/ads/images/" + filename);
                    }

                    AdEntity savedEntity = adRepository.save(entity);
                    return adMapper.toDto(savedEntity);
                });
    }

    public Optional<ExtendedAd> getAd(Integer id) {
        return adRepository.findById(id)
                .map(adMapper::toExtendedDto);
    }

    public boolean deleteAd(Integer id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return adRepository.findById(id)
                .filter(ad -> ad.getAuthor().getEmail().equals(username))
                .map(ad -> {
                    adRepository.delete(ad);
                    return true;
                })
                .orElse(false);
    }

    public Optional<Ad> updateAd(Integer id, CreateOrUpdateAd updateAd) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return adRepository.findById(id)
                .filter(ad -> ad.getAuthor().getEmail().equals(username))
                .map(ad -> {
                    ad.setTitle(updateAd.getTitle());
                    ad.setPrice(updateAd.getPrice());
                    ad.setDescription(updateAd.getDescription());
                    AdEntity savedEntity = adRepository.save(ad);
                    return adMapper.toDto(savedEntity);
                });
    }

    public Ads getUserAds() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(username)
                .map(author -> {
                    List<Ad> ads = adRepository.findAllByAuthor(author).stream()
                            .map(adMapper::toDto)
                            .collect(Collectors.toList());

                    Ads result = new Ads();
                    result.setCount(ads.size());
                    result.setResults(ads);
                    return result;
                })
                .orElse(new Ads());
    }

    public boolean updateAdImage(Integer id, MultipartFile image) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return adRepository.findById(id)
                .filter(ad -> ad.getAuthor().getEmail().equals(username))
                .map(ad -> {
                    if (image != null && !image.isEmpty()) {
                        String filename = saveImage(image);
                        ad.setImagePath("/ads/images/" + filename);
                        adRepository.save(ad);
                        return true;
                    }
                    return false;
                })
                .orElse(false);
    }

    private String saveImage(MultipartFile image) {
        try {
            String filename = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            Path filePath = imageStoragePath.resolve(filename);
            Files.copy(image.getInputStream(), filePath);
            return filename;
        } catch (IOException e) {
            throw new RuntimeException("Не удалось сохранить изображение", e);
        }
    }
}