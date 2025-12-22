package java.ru.skypro.homework.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.ru.skypro.homework.dto.newPassword;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Обновление пароля")
public class newPassword {

    @NotBlank
    @Size(min = 8, max = 16)
    @Schema(description = "текущий пароль", minLength = 8, maxLength = 16)
    private String currentPassword;

    @NotBlank
    @Size(min = 8, max = 16)
    @Schema(description = "новый пароль", minLength = 8, maxLength = 16)
    private String newPassword;
}