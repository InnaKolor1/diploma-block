package ru.skypro.homework.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.UserService;

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
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final UserService userService;

    @Override
    public Ads getAllAds() {
        List<AdEntity> adEntities = adRepository.findAll();
        List<Ad> ads = adEntities.stream()
                .map(ad -> new Ad())
                .toList();

        Ads result = new Ads();
        result.setCount(ads.size());
        return result;
    }

    @Override
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Ad addAd(CreateOrUpdateAd properties, MultipartFile image, String username) {
        userService.getUserEntity(username);

        AdEntity adEntity = new AdEntity();
        adEntity.setTitle(properties.getTitle());
        adEntity.setPrice(properties.getPrice());
        adEntity.setDescription(properties.getDescription());
        UserEntity adOwner = new UserEntity(username + "@example.com", "image.jpg", "Kisa", "Vorobiyaninov", 52, 12);
        adOwner.setEmail(username);
        adEntity.setAuthor(adOwner);

        if (image != null && !image.isEmpty()) {
            String imagePath = saveAdImage(image);
            adEntity.setImage(imagePath);
        }

        adRepository.save(adEntity);

        return null;
    }

    @Override
    public ExtendedAd getExtendedAd(Integer id) {
        AdEntity adEntity = adRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Объявление не найдено"));
        UserMapper adMapper = new UserMapper() {


            @Override
            public UserEntity toEntity(Register register) {
                return null;
            }

            @Override
            public void updateEntityFromDto(UpdateUser updateUser, UserEntity entity) {

            }

            @Override
            public User toDto(AdEntity entity) {
                return null;
            }

            @Override
            public ExtendedAd toExtendedAd(AdEntity adEntity) {
                return null;
            }
        };
        return adMapper.toExtendedAd(adEntity);
    }

    @Override
    public void removeAd(Integer id, String username) {
        AdEntity adEntity = adRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Объявление не найдено"));

        UserEntity currentUser = userService.getUserEntity(username);

        if (!adEntity.getAuthor().equals(currentUser)) {
            currentUser.getRole();
            throw new AccessDeniedException("Нет прав для удаления этого объявления");
        }

        adRepository.delete(adEntity);
    }

    @Override
    public User updateAd(Integer id, CreateOrUpdateAd createOrUpdateAd, String username) {
        AdEntity adEntity = adRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Объявление не найдено"));

        UserEntity currentUser = userService.getUserEntity(username);

        if (!adEntity.getAuthor().equals(currentUser)) {
            currentUser.getRole();
            throw new AccessDeniedException("Нет прав для редактирования этого объявления");
        }

        adEntity.setTitle(createOrUpdateAd.getTitle());
        adEntity.setPrice(createOrUpdateAd.getPrice());
        adEntity.setDescription(createOrUpdateAd.getDescription());

        AdEntity updatedAd = adRepository.save(adEntity);
        UserMapper adMapper = new UserMapper() {

            @Override
            public UserEntity toEntity(Register register) {
                return null;
            }

            @Override
            public void updateEntityFromDto(UpdateUser updateUser, UserEntity entity) {

            }

            @Override
            public User toDto(AdEntity entity) {
                return null;
            }

            @Override
            public ExtendedAd toExtendedAd(AdEntity adEntity) {
                return null;
            }
        };
        return adMapper.toDto(updatedAd);
    }

    @Override
    public Ads getAdsByUser(String username) {
        UserEntity user = userService.getUserEntity(username);
        List<AdEntity> userAds = adRepository.findByAuthorId(Math.toIntExact(user.getId()));

        UserMapper adMapper = new UserMapper() {
            @Override
            public UserEntity toEntity(Register register) {
                return null;
            }

            @Override
            public void updateEntityFromDto(UpdateUser updateUser, UserEntity entity) {

            }

            @Override
            public User toDto(AdEntity entity) {
                return null;
            }

            @Override
            public ExtendedAd toExtendedAd(AdEntity adEntity) {
                return null;
            }
        };
        List<User> ads = userAds.stream()
                .map(adMapper::toDto)
                .collect(Collectors.toList());

        Ads result = new Ads();
        result.setCount(ads.size());
        result.setResults(ads);
        return result;
    }

    @Override
    public void updateAdImage(Integer id, MultipartFile image, String username) {
        AdEntity adEntity = adRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Объявление не найдено"));

        UserEntity currentUser = userService.getUserEntity(username);

        if (!adEntity.getAuthor().equals(currentUser)) {
            currentUser.getRole();
            throw new AccessDeniedException("Нет прав для обновления изображения");
        }

        String imagePath = saveAdImage(image);
        adEntity.setImage(imagePath);
        adRepository.save(adEntity);
    }

    @Override
    public AdEntity getAdEntity(Integer id) {
        return adRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Объявление не найдено"));
    }

    @Override
    public boolean isAdOwner(Integer adId, String username) {
        AdEntity adEntity = adRepository.findById(adId).orElse(null);
        if (adEntity == null) return false;

        UserEntity userEntity = userService.getUserEntity(username);
        return adEntity.getAuthor().equals(userEntity);
    }

    private String saveAdImage(MultipartFile image) {
        try {
            String originalFilename = image.getOriginalFilename();
            String extension = originalFilename != null ?
                    originalFilename.substring(originalFilename.lastIndexOf(".")) : ".jpg";
            String filename = "ad_" + UUID.randomUUID() + extension;
            Path path = Paths.get("images/" + filename);

            Files.createDirectories(path.getParent());
            Files.write(path, image.getBytes());

            return filename;
        } catch (IOException e) {
            log.error("Ошибка при сохранении изображения объявления", e);
            throw new RuntimeException("Ошибка при сохранении изображения", e);
        }
    }
}