package ru.skypro.homework.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.skypro.homework.dto.Role;
/**
 * JPA сущность для представления пользователя в базе данных.
 * Соответствует таблице 'users' в базе данных.
 * Содержит информацию о пользователе и его связи с объявлениями и комментариями.
 */
@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(force = true)
public class UserEntity {

    private final String image;
    private String email;
    @Setter
    private String firstName;
    private String lastName;
    /**
     * Уникальный идентификатор пользователя,Роль пользователя в системе,
     * Номер телефона пользователя,пароль пользователя,
     * возраст,Уникальный идентификатор пользователя используется как логин.
     * Генерируется автоматически базой данных.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Setter
    private Role role;
    private @NotBlank
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}") String phone;
    @Setter
    private String password;
    private int age;
    private int i;
    /**
     * Email пользователя,Имя файла аватара пользователя,Имя пользователя,
     * Фамилия пользователя,возраст,Уникальный идентификатор пользователя используется как логин.
     * Должен быть уникальным и не может быть null.
     */
    public UserEntity(String email, String image, String firstName, String lastName, int age, Integer id) {
        this.email = email;
        this.image = image;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.id = id;
        this.role = Role.USER;
    }

    public UserEntity(String image, int id) {
        this.image = image;
        this.id = id;
    }

    public UserEntity(String image, String id) {
        this.image = image;
        this.id = Integer.valueOf(id);
    }

    public UserEntity(String image, Integer id) {
        this.image = image;
        this.id = id;
    }

    public String getRole() {
            return null;
    }

    public void setEmail(@NotBlank @Size(min = 4, max = 32) String username) {
        this.email = username;
    }

    public void setLastName(@NotBlank @Size(min = 3, max = 10)  String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(@NotBlank @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}") String phone) {
        this.phone = phone;
    }

    public void setId(int i) {
        this.i = i;
    }

}
