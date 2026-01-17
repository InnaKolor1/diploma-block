package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
/**
 * DTO для списка объявлений.
 * Содержит общее количество объявлений и их список.
 */
@Data
@Schema(description = "Список объявлений")
public class Ads {

    @Schema(description = "общее количество объявлений")
    private Integer count;

    @Schema(description = "список объявлений")
    private List<Ad> results;

    public void setResults(@MonotonicNonNull List<Ad> ads) {
        this.results = ads.stream()
                .map(ad -> new Ad())
                .collect(Collectors.toList());
    }
}


