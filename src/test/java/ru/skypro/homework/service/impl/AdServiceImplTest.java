package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdServiceImplTest {

    @Mock
    private AdRepository adRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private AdServiceImplTest adsService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;

    private AdServiceImplTest() {
    }

    @Test
    void getAllAds_ShouldReturnAds_WhenAdsExist() {
        AdEntity adEntity1 = new AdEntity();
        adEntity1.setId(1);
        AdEntity adEntity2 = new AdEntity();
        adEntity2.setId(2);
        List<AdEntity> adEntities = Arrays.asList(adEntity1, adEntity2);

        Ad ad1 = new Ad(1, "Test Description");
        ad1.setPk(1);
        Ad ad2 = new Ad(2, "Another Description");
        ad2.setPk(2);

        when(adRepository.findAll()).thenReturn(adEntities);


        Ads result = adsService.getAllAds();

        assertNotNull(result);
        assertEquals(2, result.getCount());
        assertEquals(2, result.getResults().size());
        verify(adRepository, times(1)).findAll();
    }

    private Ads getAllAds() {

        AdEntity adEntity1 = new AdEntity();
        adEntity1.setId(1);
        AdEntity adEntity2 = new AdEntity();
        adEntity2.setId(2);
        List<AdEntity> adEntities = Arrays.asList(adEntity1, adEntity2);

        Ad ad1 = new Ad(1, "Test Description");
        ad1.setPk(1);
        Ad ad2 = new Ad(2, "Another Description");
        ad2.setPk(2);


        when(adRepository.findAll()).thenReturn(adEntities);
        UserEntity userEntity = new UserEntity();
        when(userService.getUserEntity("kisa@example.com")).thenReturn(userEntity);
        PasswordEncoder userPasswordEncoder = mock(PasswordEncoder.class);
        when(userPasswordEncoder.encode(any())).thenReturn("encodedPassword");
        userService = new UserServiceImpl(userRepository, userPasswordEncoder);

        Ads result = adsService.getAllAds();

        assertNotNull(result);
        assertEquals(2, result.getCount());
        assertEquals(2, result.getResults().size());
        verify(adRepository, times(1)).findAll();
        return result;
    }
}
