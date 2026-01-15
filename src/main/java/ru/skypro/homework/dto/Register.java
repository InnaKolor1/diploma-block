package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Setter
@Schema(description = "Данные для регистрации")
public class Register {

    @Getter
    @NotBlank
    @Size(min = 4, max = 32)
    @Schema(description = "логин", minLength = 4, maxLength = 32)
    private String username;

    @Getter
    @NotBlank
    @Size(min = 8, max = 32)
    @Schema(description = "пароль", minLength = 8, maxLength = 32)
    private String password;

    @Getter
    @Schema(description = "роль пользователя")
    private Role role;
    private String register;

    public Register(String username, String password, String firstName, String lastName, String phone, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Register(String register) {
        this.register = register;
        this.username = getUsername();
        this.password = getPassword();
        this.role = getRole();
    }

    public Register() {
        this.username = "kisa_12@example.com";
        this.password = "FatherOfTheRussian'sDemocratic";
        this.role = Role.USER;
    }


    public String getFirstName() {
        return null;
    }

    public String getLastName() {
        return null;
    }

    public @NotBlank @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}") String getPhone() {
        return null;
    }

    public void setFirstName(String firstName) {
    }

    public void setLastName(String lastName) {
    }

    public void setPhone(String phone) {
    }

}