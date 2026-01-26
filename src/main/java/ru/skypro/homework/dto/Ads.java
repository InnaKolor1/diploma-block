package ru.skypro.homework.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Ads {

    private Integer count;
    private List<Ad> results;
}
