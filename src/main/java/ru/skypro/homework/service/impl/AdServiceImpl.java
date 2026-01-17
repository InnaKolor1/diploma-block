package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;

import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;

    private final String imageDir = "images/";

    @Override
    public Ads getAllAds() {
        List<AdEntity> adEntities = adRepository.findAll();
        Ads ads = new Ads();
        ads.setCount(adEntities.size());
        ads.setResults(adEntities.stream().map(adMapper::toDto).collect(Collectors.toList()));
        return ads;
    }

    @Override
    public Ad addAd(CreateOrUpdateAd createOrUpdateAd, MultipartFile image, String username) {
        UserEntity author = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        AdEntity adEntity = adMapper.toEntity(createOrUpdateAd);
        adEntity.setAuthor(author);

        if (image != null && !image.isEmpty()) {
            String imagePath = saveImage(image);
            adEntity.setImage(imagePath);
        }

        AdEntity savedAd = adRepository.save(adEntity);
        return adMapper.toDto(savedAd);
    }

    @Override
    public ExtendedAd getExtendedAd(Integer id) {
        AdEntity adEntity = adRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Объявление не найдено"));
        return adMapper.toExtendedAd(adEntity);
    }

    @Override
    public void removeAd(Integer id, String username) {
        AdEntity adEntity = adRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Объявление не найдено"));

        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        if (!adEntity.getAuthor().getId().equals(user.getId()) && !user.getRole().equals(Role.ADMIN)) {
            throw new RuntimeException("Недостаточно прав для удаления объявления");
        }

        adRepository.delete(adEntity);
    }

    @Override
    public Ad updateAd(Integer id, CreateOrUpdateAd createOrUpdateAd, String username) {
        AdEntity adEntity = adRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Объявление не найдено"));

        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        if (!adEntity.getAuthor().getId().equals(user.getId())) {
            throw new RuntimeException("Недостаточно прав для редактирования объявления");
        }

        adMapper.updateEntityFromDto(createOrUpdateAd, adEntity);
        AdEntity updatedAd = adRepository.save(adEntity);
        return adMapper.toDto(updatedAd);
    }

    @Override
    public Ads getAdsByUser(String username) {
        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        List<AdEntity> adEntities = adRepository.findByAuthor(user);
        Ads ads = new Ads();
        ads.setCount(adEntities.size());
        ads.setResults(adEntities.stream().map(adMapper::toDto).collect(Collectors.toList()));
        return ads;
    }

    @Override
    public void updateAdImage(Integer id, MultipartFile image, String username) {
        AdEntity adEntity = adRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Объявление не найдено"));

        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        if (!adEntity.getAuthor().getId().equals(user.getId())) {
            throw new RuntimeException("Недостаточно прав для обновления изображения объявления");
        }

        if (image != null && !image.isEmpty()) {
            String imagePath = saveImage(image);
            adEntity.setImage(imagePath);
            adRepository.save(adEntity);
        }
    }

    @Override
    public AdEntity getAdEntity(Integer adId) {
        return adRepository.findById(adId).orElse( null );
    }

    private String saveImage(MultipartFile image) {
        try {
            String originalFilename = image.getOriginalFilename();
            String extension = originalFilename != null ?
                    originalFilename.substring(originalFilename.lastIndexOf(".")) : ".jpg";
            String filename = UUID.randomUUID() + extension;
            Path path = Paths.get(imageDir + filename);

            Files.createDirectories(path.getParent());
            Files.write(path, image.getBytes());

            return filename;
        } catch (IOException e) {
            log.error("Ошибка сохранения изображения", e);
            throw new RuntimeException("Ошибка сохранения изображения", e);
        }
    }
}