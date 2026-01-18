package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(description = "Данные пользователя")
public class User {

    @Schema(description = "id пользователя")
    private Integer id;

    @Schema(description = "логин пользователя")
    private String email;

    @Schema(description = "имя пользователя")
    private String firstName;

    @Schema(description = "фамилия пользователя")
    private String lastName;

    @Schema(description = "телефон пользователя")
    private String phone;

    @Schema(description = "роль пользователя")
    private Role role;

    @Schema(description = "ссылка на аватар пользователя")
    private String image;
    private String password;

    public User(Integer id, String email, String firstName, String lastName, @NotBlank @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}") String phone, String image) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.role = Role.USER;
        this.image = image;
    }

    public User toDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.phone = user.getPhone();
        this.role = user.getRole();
        this.image = user.getImage();
        return new User(12, this.email, this.firstName, this.lastName, this.phone, this.image);
    }



    public void setRole(String role) {
        this.role = Role.valueOf(role);
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
