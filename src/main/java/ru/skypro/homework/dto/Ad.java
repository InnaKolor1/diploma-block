package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * DTO для базовой информации об объявлении.
 * Содержит основные данные для отображения в списке объявлений.
 */
@Data
@Schema(description = "Объявление")
public class Ad {
    @Schema(description = "id автора объявления")
    private Integer author;

    @Schema(description = "ссылка на картинку объявления")
    private String image;

    private Long id = null;
    private String description = "";
    @Schema(description = "id объявления")
    private Integer pk;

    @Schema(description = "цена объявления")
    private Integer price;

    @Schema(description = "заголовок объявления")
    private String title;

    public Ad() {
        this.id = null;
        this.description = "";
        this.pk = 0;
        this.title = "";
        this.price = 0;
        this.image = "";
    }

    public Ad(Long id, String description) {

        this.id = id;
        this.description = description;
    }
}
