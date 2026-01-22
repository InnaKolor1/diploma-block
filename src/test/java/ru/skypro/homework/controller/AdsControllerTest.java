package ru.skypro.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.skypro.homework.config.TestSecurityConfig;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.service.AdService;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdsController.class)
@Import(TestSecurityConfig.class)
class AdsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AdService adService;

    @Test
    @WithMockUser
    void getAllAds_ShouldReturnOk() throws Exception {
        Ads ads = new Ads();
        ads.setCount(0);
        ads.setResults(Collections.emptyList());

        when(adService.getAllAds()).thenReturn(ads);

        mockMvc.perform(get("/ads"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void getAdsMe_ShouldReturnOk() throws Exception {
        Ads ads = new Ads();
        ads.setCount(0);
        ads.setResults(Collections.emptyList());

        when(adService.getAdsByUser(any())).thenReturn(ads);

        mockMvc.perform(get("/ads/me"))
                .andExpect(status().isOk());
    }
}