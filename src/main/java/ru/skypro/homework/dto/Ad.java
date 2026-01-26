package ru.skypro.homework.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ad {
    private Integer pk;
    private String title;
    private String description;
    private Integer price;
    private String image;
    private Integer author;
}


