package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.skypro.homework.entity.Role;

@Data
@Schema(description = "Данные для регистрации")
public class Register {

    @NotBlank
    @Size(min = 4, max = 32)
    @Schema(description = "логин", minLength = 4, maxLength = 32)
    private String username;

    @NotBlank
    @Size(min = 8, max = 32)
    @Schema(description = "пароль", minLength = 8, maxLength = 32)
    private String password;

    @NotBlank
    @Size(min = 3, max = 10)
    @Schema(description = "имя", minLength = 3, maxLength = 10)
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 10)
    @Schema(description = "фамилия", minLength = 3, maxLength = 10)
    private String lastName;

    @NotBlank
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    @Schema(description = "телефон")
    private String phone;

    @Schema(description = "роль пользователя")
    private Role role = Role.USER;
}