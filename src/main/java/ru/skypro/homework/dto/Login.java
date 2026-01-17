package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
/**
 * DTO для аутентификации пользователя.
 * Содержит учетные данные для входа в систему.
 */
@Data
@Schema(description = "Данные для авторизации")
public class Login {

    @NotBlank
    @Size(min = 8, max = 16)
    @Schema(description = "пароль", minLength = 8, maxLength = 16)
    private String password;

    @NotBlank
    @Size(min = 4, max = 32)
    @Schema(description = "логин", minLength = 4, maxLength = 32)
    private String username;
}
