package ru.skypro.homework.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment {

    private Integer pk;
    private String text;
    private Long createdAt;
    private Integer author;
    private String authorFirstName;
    private String authorImage;
    private Integer adId;
}
