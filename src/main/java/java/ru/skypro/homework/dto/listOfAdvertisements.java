package java.ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;import java.ru.skypro.homework.dto.listOfAdvertisements;

@Data
@Schema(description = "Список объявлений")
public class listOfAdvertisements {

    @Schema(description = "общее количество объявлений")
    private Integer count;

    @Schema(description = "список объявлений")
    private List<listOfAdvertisements> results;
}
