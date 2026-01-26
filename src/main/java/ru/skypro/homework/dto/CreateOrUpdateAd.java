package ru.skypro.homework.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrUpdateAd {

    private String title;
    private Integer price;
    private String description;
}
