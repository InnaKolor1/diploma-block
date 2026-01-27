package ru.skypro.homework.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExtendedAdDto {

    private Integer pk;
    private String title;
    private String description;
    private Integer price;
    private String image;

    private Integer authorId;
    private String authorFirstName;
    private String authorLastName;
    private String email;
    private String phone;
}
