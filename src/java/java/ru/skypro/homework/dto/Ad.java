package java.ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.xml.stream.events.Comment;
import java.util.List;

@Data
@Schema(description = "Список комментариев")
public class Ad {

    @Schema(description = "общее количество комментариев")
    private Integer count;

    @Schema(description = "список комментариев")
    private List<Comment> results;
}