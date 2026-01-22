package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;

import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.mapper.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.UserService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdServiceImplTest {

    @Mock
    private AdRepository adRepository;

    @Mock
    private UserService userService;

    @Mock
    private AdMapper adMapper;

    @InjectMocks
    private AdServiceImpl adService;

    @Test
    void getAllAds_ShouldReturnAds() {

        AdEntity adEntity = new AdEntity();
        adEntity.setId(1);

        Ad adDto = new Ad();
        adDto.setPk(1);

        when(adRepository.findAll()).thenReturn(List.of(adEntity));
        when(adMapper.toDto(adEntity)).thenReturn(adDto);

        Ads result = adService.getAllAds();

        assertNotNull(result);
        assertEquals(1, result.getCount());
        verify(adRepository, times(1)).findAll();
    }

    @Test
    void getExtendedAd_ShouldReturnAd_WhenExists() {
        Integer adId = 1;
        AdEntity adEntity = new AdEntity();
        adEntity.setId(adId);

        when(adRepository.findById(adId)).thenReturn(Optional.of(adEntity));

        assertDoesNotThrow(() -> adService.getExtendedAd(adId));

        verify(adRepository, times(1)).findById(adId);
    }
}